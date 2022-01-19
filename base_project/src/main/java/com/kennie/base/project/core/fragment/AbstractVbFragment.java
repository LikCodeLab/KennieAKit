package com.kennie.base.project.core.fragment;

import androidx.viewbinding.ViewBinding;

import com.kennie.base.project.core.activity.AbstractBaseVMActivity;
import com.kennie.base.project.core.viewmodel.BaseViewModel;

/**
 * Author：Kennie
 * Project：KennieAKit
 * Class：AbstractVbFragment
 * Date：2021/12/12 23:15
 * Desc：包含 ViewModel 和 ViewBinding ViewModelFragment基类，将ViewModel自动注入Fragment和 ViewBinding
 * 需要使用 ViewBinding的继承
 */
public abstract class AbstractVbFragment<VM extends BaseViewModel, VB extends ViewBinding> extends AbstractBaseVMActivity<VM> {
}
