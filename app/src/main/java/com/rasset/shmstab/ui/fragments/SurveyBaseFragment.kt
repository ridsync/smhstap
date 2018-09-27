package com.rasset.shmstab.ui.fragments

import com.rasset.shmstab.model.DiagnoseBaseInfo
import com.rasset.shmstab.model.DiagnoseInfo

/**
 * Created by devok on 2018-09-27.
 */
open abstract class SurveyBaseFragment : BaseFragment(){

    abstract fun isValidDiagInputs(): Boolean

    abstract fun getDiagDatas():DiagnoseBaseInfo?

}
