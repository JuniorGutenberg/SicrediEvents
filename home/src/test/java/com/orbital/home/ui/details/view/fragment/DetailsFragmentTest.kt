package com.orbital.home.ui.details.view.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.orbital.core.data.base.DataState
import com.orbital.core.utils.ValidateUtils
import com.orbital.home.data.details.remote.model.DetailsRequest
import com.orbital.home.data.details.repository.DetailsRepository
import com.orbital.home.schedulers.RxImmediateSchedulerRule
import com.orbital.home.ui.details.viewModel.DetailsViewModel
import com.orbital.home.ui.details.viewModel.DetailsViewModelImpl
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailsFragmentTest {
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var schedulers = RxImmediateSchedulerRule()

    @Mock
    lateinit var repository: DetailsRepository

    @Mock
    lateinit var observer: Observer<DataState<String>>

    lateinit var viewModel: DetailsViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = DetailsViewModelImpl(repository)
    }

    @After
    fun tearDown(){
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `when email is valid`(){
        val emailCorrect = "nome@email.com"
        val emailIncorrect = "aalalla"

        Assert.assertTrue(ValidateUtils.isValidateEmail(emailCorrect))
    }

    @Test
    fun `when email is invalid`(){
        val emailCorrect = "nome@email.com"
        val emailIncorrect = "aalalla"

        Assert.assertFalse(ValidateUtils.isValidateEmail(emailIncorrect))
    }

    @Test
    fun `when name is valid`(){
        val nameCorrect = "Junior Gutenberg"

        // More 45 chars
        val nameIncorrect = "aalallajhsagjhgsfhjgsafhjgsavfdygsjhghsagcsagdyusgacdugasdcyusagudgsaygcdg"

        Assert.assertTrue(ValidateUtils.isValidateName(nameCorrect,45))
    }

    @Test
    fun `when name is invalid`(){
        val nameCorrect = "Junior Gutenberg"

        // More 45 chars
        val nameIncorrect = "aalallajhsagjhgsfhjgsafhjgsavfdygsjhghsagcsagdyusgacdugasdcyusagudgsaygcdg"

        Assert.assertFalse(ValidateUtils.isValidateName(nameIncorrect,45))
    }

    @Test
    fun `assert data equals`(){
        val body = DetailsRequest.Body(
            "1",
            "Gutenberg",
            "aleksiam_junior@hotmail.com"
        )
        whenever(repository.checkIn(body)).thenReturn(Single.just(""))

        viewModel.check.observeForever(observer)
        viewModel.checkIn(body)

        val data = viewModel.check.value as DataState.Success


        assert(data.data == "")
    }

    @Test
    fun `when data is error`(){
        val body = DetailsRequest.Body(
            "1",
            "Gutenberg",
            "aleksiam_junior@hotmail.com"
        )

        whenever(repository.checkIn(body)).thenReturn(Single.error(Throwable()))

        viewModel.check.observeForever(observer)
        viewModel.checkIn(body)

        val data = viewModel.check.value as DataState.Error


        verify(observer, times(1)).onChanged(data)
    }
}