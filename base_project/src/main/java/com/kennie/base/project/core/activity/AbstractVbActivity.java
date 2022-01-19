package com.kennie.base.project.core.activity;

import androidx.viewbinding.ViewBinding;

import com.kennie.base.project.core.viewmodel.BaseViewModel;

/**
 * Author：Kennie
 * Project：KennieAKit
 * Class：AbstractVbActivity
 * Date：2021/12/12 23:15
 * Desc：包含 ViewModel 和 ViewBinding ViewModelActivity基类，把ViewModel 和 ViewBinding 注入进来了
 * 需要使用 ViewBinding 的请继承它
 */
public abstract class AbstractVbActivity<VM extends BaseViewModel, VB extends ViewBinding> extends AbstractBaseVMActivity<VM> {
}
