package com.orbital.home.ui.events.view.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.orbital.core.data.base.DataState
import com.orbital.home.data.events.remote.model.EventsResponse
import com.orbital.home.data.events.repository.EventsRepository
import com.orbital.home.schedulers.RxImmediateSchedulerRule
import com.orbital.home.ui.events.viewModel.EventsViewModel
import com.orbital.home.ui.events.viewModel.EventsViewModelImpl
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HomeFragmentTest {
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var schedulers = RxImmediateSchedulerRule()

    @Mock
    lateinit var repository: EventsRepository

    @Mock
    lateinit var observer: Observer<DataState<List<EventsResponse.Body>>>

    lateinit var viewModel: EventsViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = EventsViewModelImpl(repository)
    }

    @After
    fun tearDown(){
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()

    }

    @Test
    fun `assert data equals`(){
        val list = mutableListOf(EventsResponse.Body(
            "1","titulo1",
            2.55,-88.0,10.00,
            "image","descrição",1000))


        whenever(repository.getEvents()).thenReturn(Single.just(list))

        viewModel.events.observeForever(observer)
        viewModel.getEvents()

        val data = viewModel.events.value as DataState.Success


        assert(data.data == list)
    }

    @Test
    fun `when data is error`(){

        whenever(repository.getEvents()).thenReturn(Single.error(Throwable()))

        viewModel.events.observeForever(observer)
        viewModel.getEvents()

        val data = viewModel.events.value as DataState.Error


        verify(observer, times(1)).onChanged(data)
    }
}