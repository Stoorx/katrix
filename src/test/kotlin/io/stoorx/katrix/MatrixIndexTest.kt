package io.stoorx.katrix

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("MatrixIndex")
class MatrixIndexTest {
    @Nested
    @DisplayName("equals")
    inner class Equals {
        @Test
        fun `equals with self`() {
            val mi = MatrixIndex(0, 0)
            assertTrue(mi == mi)
        }

        @Test
        fun `not equals with different type`() {
            assertFalse(MatrixIndex(0, 0) == Any())
        }

        @Test
        fun `not equals with different matrix index`() {
            assertFalse(MatrixIndex(0, 0) == MatrixIndex(1, 1))
        }
    }

    @Nested
    @DisplayName("hashCode")
    inner class HashCode {
        @Test
        fun `equal hashCode`() {
            assertTrue(MatrixIndex(0, 0).hashCode() == MatrixIndex(0, 0).hashCode())
            assertTrue(MatrixIndex(1, 1).hashCode() == MatrixIndex(1, 1).hashCode())
            assertTrue(MatrixIndex(-1, -1).hashCode() == MatrixIndex(-1, -1).hashCode())
            assertTrue(MatrixIndex(11, 12).hashCode() == MatrixIndex(11, 12).hashCode())
            assertTrue(MatrixIndex(-79, -24).hashCode() == MatrixIndex(-79, -24).hashCode())
        }

        @Test
        fun `different hasCode`() {
            assertTrue(MatrixIndex(-79, -24).hashCode() != MatrixIndex(5, 7).hashCode())
        }
    }
}