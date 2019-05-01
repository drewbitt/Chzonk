package com.teamb.chzonk.data.model

abstract class BookData {
    abstract var autoId: Int
    abstract var title: String
    abstract var currentPage: Int
    abstract var filePath: String
    abstract var isFinished: Boolean
}
