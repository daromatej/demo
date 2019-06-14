package com.matejdev.demo.data.vo

interface Vo {
    interface Post {
        companion object {
            const val ID = "id"
            const val USER_ID = "userId"
            const val TITLE = "title"
            const val BODY = "body"
        }
    }

    interface User {
        companion object {
            const val ID = "id"
            const val NAME = "name"
            const val EMAIL = "email"
        }
    }

    interface Comment {
        companion object {
            const val ID = "id"
            const val POST_ID = "postId"
            const val NAME = "name"
            const val EMAIL = "email"
            const val BODY = "body"
        }
    }
}
