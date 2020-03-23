package io.stoorx.katrix

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("MatrixSize")
class MatrixSizeTest {
    @Nested
    @DisplayName("equals")
    inner class Equals {
        @Test
        fun `equals with self`() {
            val ms = MatrixSize(0, 0)
            assertTrue(ms == ms)
        }

        @Test
        fun `not equals with different type`() {
            assertFalse(MatrixSize(0, 0) == Any())
        }

        @Test
        fun `not equals with different matrix index`() {
            assertFalse(MatrixSize(0, 0) == MatrixSize(1, 1))
        }
    }

    @Nested
    @DisplayName("hashCode")
    inner class HashCode {
        @Test
        fun `equal hashCode`() {
            assertTrue(MatrixSize(0, 0).hashCode() == MatrixSize(0, 0).hashCode())
            assertTrue(MatrixSize(1, 1).hashCode() == MatrixSize(1, 1).hashCode())
            assertTrue(MatrixSize(-1, -1).hashCode() == MatrixSize(-1, -1).hashCode())
            assertTrue(MatrixSize(11, 12).hashCode() == MatrixSize(11, 12).hashCode())
            assertTrue(MatrixSize(-79, -24).hashCode() == MatrixSize(-79, -24).hashCode())
        }

        @Test
        fun `different hasCode`() {
            assertTrue(MatrixSize(-79, -24).hashCode() != MatrixSize(5, 7).hashCode())
        }
    }
}