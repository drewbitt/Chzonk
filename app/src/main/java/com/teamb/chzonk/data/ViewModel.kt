package com.teamb.chzonk.data
import androidx.lifecycle.ViewModel
import com.teamb.chzonk.data.repository.LocallibRepository
import com.teamb.chzonk.data.repository.ReaderRepository

class ViewModel(
    internal var locallibRepository: LocallibRepository,
    internal var readerRepository: ReaderRepository
) : ViewModel()
