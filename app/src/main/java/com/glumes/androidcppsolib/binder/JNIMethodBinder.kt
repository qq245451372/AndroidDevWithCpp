package com.glumes.androidcppsolib.binder

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glumes.androidcppsolib.R
import com.glumes.androidcppsolib.databinding.ItemJniMethodBinding
import com.glumes.androidcppsolib.handler.JNIOperationHandler
import com.glumes.cppso.utils.NO_NATIVE_OPERATION
import com.glumes.databindingadapter.BindingViewHolder
import com.glumes.databindingadapter.ViewHolderBinder

/**
 * Created by glumes on 28/02/2018
 */
data class SampleModel(var content: String, val type: Int = NO_NATIVE_OPERATION)


class SampleViewHolder(binding: ItemJniMethodBinding?) : BindingViewHolder<SampleModel, ItemJniMethodBinding>(binding) {

    override fun onBind(data: SampleModel?, position: Int) {
        mBinding.model = data!!
        mBinding.executePendingBindings()
        mBinding.eventhandler = NativeEventHandler()
    }
}


class SampleBinder : ViewHolderBinder<SampleModel, SampleViewHolder>() {

    override fun onBindViewHolder(holder: SampleViewHolder?, data: SampleModel?, position: Int) {
        holder!!.onBind(data, position)
    }

    override fun createViewHolder(inflater: LayoutInflater?, parent: ViewGroup?): SampleViewHolder {
        val mBinding = DataBindingUtil.inflate(inflater!!, R.layout.item_jni_method, parent, false) as ItemJniMethodBinding
        return SampleViewHolder(mBinding)
    }
}


class NativeEventHandler {

    fun onClick(view: View, model: SampleModel) {
        JNIOperationHandler.handle(model.type)
    }
}