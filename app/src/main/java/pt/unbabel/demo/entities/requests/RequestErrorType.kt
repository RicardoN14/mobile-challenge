package pt.unbabel.demo.entities.requests

/**
 * Created by Ricardo Neves on 16/09/2019$.
 */

enum class RequestErrorType {
    INTERNET_CONNECTION_ERROR,
    TIMEOUT_ERROR,
    SERVER_ERROR,
    PARSE_ERROR,
    SSL_ERROR
}