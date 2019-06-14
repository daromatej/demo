package com.matejdev.demo.base.values

interface Const {

    interface String {
        companion object {
            const val EMPTY = ""
            const val COMMA = ","
        }
    }

    interface Int {
        companion object {
            const val ID_ABSENT = -1
        }
    }
}
