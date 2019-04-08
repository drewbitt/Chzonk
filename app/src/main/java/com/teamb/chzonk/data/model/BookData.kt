package com.teamb.chzonk.data.model

abstract class BookData {
    abstract var autoId: Int
    abstract var id: Int
    abstract var title: String
    abstract var content: String
    abstract var currentPage: Int
    abstract var totalPages: Int
    abstract var filePath: String
    abstract var isFinished: Boolean
}
