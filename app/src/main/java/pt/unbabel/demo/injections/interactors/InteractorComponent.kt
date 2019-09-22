package pt.unbabel.demo.injections.interactors

import dagger.Component
import pt.unbabel.demo.presenters.implementations.comments.CommentsPresenter
import pt.unbabel.demo.presenters.implementations.posts.PostsPresenter
import pt.unbabel.demo.presenters.implementations.users.UsersPresenter

/**
 * Created by Ricardo Neves on 16/09/2019.
 */

@Component(modules = [(InteractorModule::class)])
interface InteractorComponent {
    fun inject(postsPresenter: PostsPresenter)
    fun inject(commentsPresenter: CommentsPresenter)
    fun inject(usersPresenter: UsersPresenter)
}