package com.lkgit;

import java.util.LinkedList;
import java.util.List;

/**
 * 注释：提交类型数据设置
 */
public class DataSettings {
    private List<TypeAlias> typeAliases;

    /**
     * 注释：获取提交类型
     * 时间：2020/12/15 0015 15:00
     * 作者：郭翰林
     *
	 * # 1. feature：新功能（feature）
	 * # 2. fix：修补bug
	 * #   2.1 编号：bugtags编号/自查bug（0000）
	 * #   2.2 bug类型：
	 * #       2.2.1：YL（遗留bug）
	 * #       2.2.2：XZ（新增）
	 * #       2.2.3：XF（修复产生的新bug）
	 * #   2.3 业务类型：
	 * #       2.3.1：UI   （界面问题）
	 * #       2.3.2：LOGIC（逻辑问题）
	 * #   2.4 版本号：
	 * #       例如：v3.6.0
	 * #   2.5 是否需要优化标识：
	 * #       2.5.1 OPTIMIZING （有需要优化的点）
	 * # 3. docs：文档（documentation）
	 * # 4. style： 格式（不影响代码运行的变动）
	 * # 5. refactor：重构（即不是新增功能，也不是修改bug的代码变动）
	 * # 6. test：增加测试
	 * # 7. chore：构建过程或辅助工具的变动
	 *
	 * 	  FEAT("新功能", "A new feature"),
	 *    FIX("Bug修复", "A bug fix"),
	 *    DOCS("文档改动", "Documentation only changes"),
	 *    STYLE("格式化", "Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)"),
	 *    REFACTOR("代码重构", "A code change that neither fixes a bug nor adds a feature"),
	 *    PERF("性能优化", "A code change that improves performance"),
	 *    TEST("代码测试", "Adding missing tests or correcting existing tests"),
	 *    BUILD("项目构建", "Changes that affect the build system or external dependencies (example scopes: gulp, broccoli, npm)"),
	 *    CI("持续集成", "Changes to our CI configuration files and scripts (example scopes: Travis, Circle, BrowserStack, SauceLabs)"),
	 *    CHORE("其他", "Other changes that don't modify src or test files"),
	 *    REVERT("撤销", "Reverts a previous commit");
     * @return
     */
    public List<TypeAlias> getTypeAliases() {
        if (typeAliases == null) {
            typeAliases = new LinkedList<>();
            typeAliases.add(new TypeAlias("【新增功能】", "新的功能点、新的需求"));
            typeAliases.add(new TypeAlias("【Bug修复】", "修复的Bug:现网发散Bug、测试阶段的Bug、验收阶段的Bug"));
            typeAliases.add(new TypeAlias("【代码完善】", "开发自测过程中遗漏的逻辑"));
            typeAliases.add(new TypeAlias("【文档修改】", "只是修改了文档:注释、README.md等"));
            typeAliases.add(new TypeAlias("【样式修改】", "不影响代码功能的修改:CSS样式、代码格式化等"));
            typeAliases.add(new TypeAlias("【代码重构】", "代码更改既不修复错误也不添加功能"));
            typeAliases.add(new TypeAlias("【性能优化】", "代码更改可以提高性能"));
            typeAliases.add(new TypeAlias("【测试代码】", "添加缺失测试或更正现有测试"));
            typeAliases.add(new TypeAlias("【编译代码】", "影响构建系统或外部依赖项的更改:build.gradle、package.json、Podfile等"));
            typeAliases.add(new TypeAlias("【持续集成】", "我们的CI配置文件和脚本的更改:Jenkinsfile等"));
            typeAliases.add(new TypeAlias("【回退更改】", "代码回退提交更改"));
            typeAliases.add(new TypeAlias("【其他提交】", "除以上所有类型之外的提交更改"));
        }
        return typeAliases;
    }

    /**
     * 注释：设置提交类型
     * 时间：2020/12/15 0015 15:00
     * 作者：郭翰林
     *
     * @param typeAliases
     */
    public void setTypeAliases(List<TypeAlias> typeAliases) {
        this.typeAliases = typeAliases;
    }
}
