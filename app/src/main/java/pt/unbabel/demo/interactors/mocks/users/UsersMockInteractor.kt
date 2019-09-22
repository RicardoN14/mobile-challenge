package pt.unbabel.demo.interactors.mocks.users

import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.http.entities.*
import pt.unbabel.demo.interactors.interfaces.users.IUsersInteractor
import pt.unbabel.demo.interactors.listeners.users.IUsersInteractorListener
import pt.unbabel.demo.interactors.mocks.base.MockInteractor

class UsersMockInteractor : MockInteractor<IUsersInteractorListener>(), IUsersInteractor {

    override fun requestUsers(requestConfig: RequestConfig) {
        executeWithDelay({
            interactorListener.onRequestUsersSuccess(getUsersResponseData())
        }, requestConfig = requestConfig)
    }

    private fun getUsersResponseData() = arrayListOf<UserResponseData>().apply {

        add(
            UserResponseData(
                1, "Leanne Graham", "Bret", "Sincere@april.biz",
                AddressResponseData(
                    "Kulas Light", "Apt. 556", "Gwenborough", "92998-38740",
                    GeoResponseData(-37.3159, 81.1496)
                ), "1-770-736-8031 x56442", "hildegard.or",
                CompanyResponseData("companyName", "catchPhrase", "harness real-time e-markets")
            )
        )

        add(
            UserResponseData(
                2, "name", "username", "email",
                AddressResponseData(
                    "street", "suite", "city", "92998-38740",
                    GeoResponseData(-43.9509, -34.4618)
                ), "921111111", "website",
                CompanyResponseData("companyName", "catchPhrase", "harness real-time e-markets")
            )
        )

    }

}