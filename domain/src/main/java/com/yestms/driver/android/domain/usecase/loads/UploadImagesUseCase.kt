package com.yestms.driver.android.domain.usecase.loads

import android.content.ContentResolver
import android.net.Uri
import com.yestms.driver.android.domain.global.UseCaseWithSixParams
import com.yestms.driver.android.domain.repository.LoadsRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UploadImagesUseCase @Inject constructor(
    private val repository: LoadsRepository
) : UseCaseWithSixParams<Int, Uri, Uri?, Uri?, ContentResolver, Unit>(Dispatchers.IO) {

    override suspend fun execute(
        parameter1: Int,
        parameter2: Uri,
        parameter3: Uri?,
        parameter4: Uri?,
        parameter5: ContentResolver
    ) {
        repository.uploadImages(parameter1, parameter2, parameter3, parameter4, parameter5)
    }
}