package io.stoorx.katrix.views

import io.stoorx.katrix.Matrix
import io.stoorx.katrix.MutableMatrix

interface MatrixView<T> : Matrix<T> {

}

interface MutableMatrixView<T> : MatrixView<T>,
    MutableMatrix<T> {

}