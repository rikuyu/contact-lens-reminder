package io.github.rikuyu.contactlensreminder.data.repository

import com.google.common.truth.Truth.assertThat
import io.github.rikuyu.contactlensreminder.domain.local.DataSource
import io.github.rikuyu.contactlensreminder.domain.repository.AppSettingRepository
import io.github.rikuyu.contactlensreminder.ui.theme.ThemeColor
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class AppSettingRepositoryTest {

    @MockK
    lateinit var localDataSource: DataSource

    lateinit var repository: AppSettingRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = AppSettingRepositoryImpl(localDataSource)
    }

    @Test
    fun `logEvent Test`() {
        every { localDataSource.logEvent(any()) } returns Unit

        repository.logEvent("")

        verify(exactly = 1) {
            localDataSource.logEvent(any())
            repository.logEvent("")
        }
    }

    @Test
    fun `getThemeColor Test`() {
        every { localDataSource.getThemeColor() } returns ThemeColor.Blue.name.lowercase()

        assertThat(repository.getThemeColor()).isEqualTo(ThemeColor.Blue.name.lowercase())

        verify(exactly = 1) {
            localDataSource.getThemeColor()
            repository.getThemeColor()
        }
    }

    @Test
    fun `saveThemeColor Test`() {
        every { localDataSource.saveThemeColor(any()) } returns Unit

        repository.saveThemeColor("")

        verify(exactly = 1) {
            localDataSource.saveThemeColor(any())
            repository.saveThemeColor("")
        }
    }
}