package com.want.base.sdk.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * modified form Trinea
 *
 * @author trinea
 * @date 2014-12-10
 */
public class ShellUtil {
    public static final String COMMAND_SU = "su";
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";

    private ShellUtil() {
        //no instance
    }

    /**
     * check whether has root permission
     */
    public static boolean hasRoot() {
        return exec("echo root", true, false).result == 0;
    }

    public static Result exec(String command) {
        return exec(command, false);
    }

    public static Result exec(String command, boolean isRoot) {
        return exec(new String[]{command}, isRoot, true);
    }

    public static Result exec(String command, boolean isRoot, boolean isNeedResultMsg) {
        return exec(new String[]{command}, isRoot, isNeedResultMsg);
    }

    public static Result exec(List<String> commands, boolean isRoot, boolean isNeedResultMsg) {
        return exec(commands == null ? null : commands.toArray(new String[]{}),
                    isRoot,
                    isNeedResultMsg);
    }

    /**
     * execute shell commands
     * {@link Result#result} is -1, there maybe some excepiton.
     *
     * @param commands     command array
     * @param isRoot       whether need to run with root
     * @param needResponse whether need result msg
     */
    public static Result exec(String[] commands, boolean isRoot, boolean needResponse) {
        int result = -1;
        if (commands == null || commands.length == 0) {
            return new Result(result, null, "空命令");
        }

        Process process = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;

        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec(isRoot ? COMMAND_SU : COMMAND_SH);
            os = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (command == null) {
                    continue;
                }

                // donnot use os.writeBytes(commmand), avoid chinese charset error
                os.write(command.getBytes());
                os.writeBytes(COMMAND_LINE_END);
                os.flush();
            }
            os.writeBytes(COMMAND_EXIT);
            os.flush();

            result = process.waitFor();
            if (needResponse) {
                successMsg = new StringBuilder();
                errorMsg = new StringBuilder();
                successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
                errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String s;
                while ((s = successResult.readLine()) != null) {
                    successMsg.append(s);
                }
                while ((s = errorResult.readLine()) != null) {
                    errorMsg.append(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (errorResult != null) {
                    errorResult.close();
                }
                if (successResult != null) {
                    successResult.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (process != null) {
                    process.destroy();
                }
            }

        }
        return new Result(result,
                          successMsg == null ? null : successMsg.toString(),
                          errorMsg == null ? null : errorMsg.toString());
    }

    public static class Result {

        public int result;
        public String responseMsg;
        public String errorMsg;

        public Result(int result) {
            this.result = result;
        }

        public Result(int result, String responseMsg, String errorMsg) {
            this.result = result;
            this.responseMsg = responseMsg;
            this.errorMsg = errorMsg;
        }
    }
}