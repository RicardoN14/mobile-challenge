package pt.unbabel.demo.injections.interactors

import dagger.Module
import dagger.Provides
import pt.unbabel.demo.helpers.isMockFlavor
import pt.unbabel.demo.interactors.mocks.posts.PostsMockInteractor
import pt.unbabel.demo.interactors.server.posts.PostsServerInteractor

/**
 * Created by Ricardo Neves on 16/09/2019.
 */

@Module
class InteractorModule {

    @Provides
    fun provideLoginInteractor() =
        if (isMockFlavor) PostsMockInteractor() else PostsServerInteractor()

}