package pt.unbabel.demo.injections.interactors

import dagger.Component
import pt.unbabel.demo.presenters.implementations.posts.PostsPresenter

/**
 * Created by Ricardo Neves on 16/09/2019$.
 */

@Component(modules = [(InteractorModule::class)])
interface InteractorComponent {
    fun inject(postsPresenter: PostsPresenter)
}