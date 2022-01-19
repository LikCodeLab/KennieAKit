package com.kennie.base.project.core.fragment;

import androidx.databinding.ViewDataBinding;

import com.kennie.base.project.core.activity.AbstractBaseVMActivity;
import com.kennie.base.project.core.viewmodel.BaseViewModel;

/**
 * Author：Kennie
 * Project：KennieAKit
 * Class：AbstractDbFragment
 * Date：2021/12/12 23:15
 * Desc：包含 ViewModel 和 ViewBinding ViewModelFragment基类，将ViewModel自动注入Fragment和 ViewDataBinding
 * 需要使用 DataBinding的继承
 */
public abstract class AbstractDbFragment<VM extends BaseViewModel, DB extends ViewDataBinding> extends AbstractBaseVMActivity<VM> {
}
