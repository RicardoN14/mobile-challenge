package pt.unbabel.demo.injections.interactors

import dagger.Module
import dagger.Provides
import pt.unbabel.demo.helpers.isMockFlavor
import pt.unbabel.demo.interactors.mocks.comments.CommentsMockInteractor
import pt.unbabel.demo.interactors.mocks.posts.PostsMockInteractor
import pt.unbabel.demo.interactors.mocks.users.UsersMockInteractor
import pt.unbabel.demo.interactors.server.comments.CommentsServerInteractor
import pt.unbabel.demo.interactors.server.posts.PostsServerInteractor
import pt.unbabel.demo.interactors.server.users.UsersServerInteractor

/**
 * Created by Ricardo Neves on 16/09/2019.
 */

@Module
class InteractorModule {

    @Provides
    fun providePostsInteractor() =
        if (isMockFlavor) PostsMockInteractor() else PostsServerInteractor()

    @Provides
    fun provideCommentsInteractor() =
        if(isMockFlavor) CommentsMockInteractor() else CommentsServerInteractor()

    @Provides
    fun provideUsersInteractor() =
        if(isMockFlavor) UsersMockInteractor() else UsersServerInteractor()

}