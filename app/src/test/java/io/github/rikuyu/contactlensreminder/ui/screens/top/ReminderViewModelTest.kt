package io.github.rikuyu.contactlensreminder.ui.screens.top

import com.google.common.truth.Truth.assertThat
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import io.github.rikuyu.contactlensreminder.domain.usecase.reminder.*
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ReminderViewModelTest {

    lateinit var viewModel: ReminderViewModel
    lateinit var useCase: ReminderUseCase

    @MockK
    lateinit var saveReminderSetting: SaveReminderSetting

    @MockK
    lateinit var startReminder: StartReminder

    @MockK
    lateinit var getReminderSetting: GetReminderSetting

    @MockK
    lateinit var cancelReminder: CancelReminder

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = ReminderUseCase(
            saveReminderSetting,
            startReminder,
            getReminderSetting,
            cancelReminder
        )
    }

    private val defaultReminderValue = ReminderValue()

    private val startedReminderValue = defaultReminderValue.copy(
        isUsingContactLens = !defaultReminderValue.isUsingContactLens,
        lensPeriod = (defaultReminderValue.lensPeriod + 6)
    )

    private val expectedReminderValue = defaultReminderValue.copy(
        isUsingContactLens = true,
        lensRemainingDays = 20
    )

    private val event: ReminderEvent = ReminderEvent.StartReminder(startedReminderValue)

    @Test
    fun `onEvent test`() {
        every { getReminderSetting.invoke() } returns defaultReminderValue
        every { saveReminderSetting.invoke(any()) } returns Unit
        every { startReminder.invoke() } returns Unit
        viewModel = ReminderViewModel(useCase)

        assertThat(viewModel.reminder.value).isEqualTo(defaultReminderValue)

        viewModel.onEvent(event)

        assertThat(viewModel.reminder.value).isEqualTo(expectedReminderValue)

        verify(exactly = 1) {
            getReminderSetting.invoke()
            saveReminderSetting.invoke(any())
            viewModel.onEvent(event)
            startReminder.invoke()
        }
    }
}