package io.stoorx.katrix.linearAlgebra

import io.stoorx.katrix.Matrix
import io.stoorx.katrix.PackedMatrix

infix fun PackedMatrix<Double>.mul(right: Matrix<Double>) {
    if (this.size.columns == right.size.rows)
        PackedMatrix(this.size.rows, right.size.columns) {
            var res = 0.0
            for (i in 0 until this.size.columns) {
                res += this[it.row, i] * right[i, it.column]
            }
            res
        }
    else throw Exception("Matrices size incorrect") // TODO: Specialized exception
}


