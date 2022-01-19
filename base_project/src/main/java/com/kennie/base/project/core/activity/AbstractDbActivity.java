package com.kennie.base.project.core.activity;

import androidx.databinding.ViewDataBinding;

import com.kennie.base.project.core.viewmodel.BaseViewModel;

/**
 * Author：Kennie
 * Project：KennieAKit
 * Class：AbstractVbActivity
 * Date：2021/12/12 23:15
 * Desc：包含 ViewModel 和 ViewBinding ViewModelActivity基类，把ViewModel 和 ViewBinding 注入进来了
 * 需要使用 DataBinding 的请继承它
 */
public abstract class AbstractDbActivity<VM extends BaseViewModel, DB extends ViewDataBinding> extends AbstractBaseVMActivity<VM> {
}
