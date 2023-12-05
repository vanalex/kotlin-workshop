package domain

import arrow.core.*

data class ValidationError(val message: String)

typealias ValidationErrors = NonEmptyList<ValidationError>
@JvmInline
value class Email(val value: String){
    companion object {
        fun valueOf(value: String?): Validated<ValidationErrors, Email> =
            when {
                value == null -> ValidationError("email should not be null").invalidNel()
                isValidEmail(value) -> Email(value).valid()
                else -> ValidationError("'$value' should be a valid email address").invalidNel()
            }


        private fun isValidEmail(value: String) =
            value.contains('@')
    }
}
data class EmailRoute(
    val from: Email,
    val to: Email,
    val cc:List<Email>,
    val bbc: List<Email>
)

fun main(){
    val notAnEmail: Validated<ValidationErrors, Email> = Email.valueOf("not an email")

    notAnEmail
        //just for debug
        .tapInvalid { errors -> errors.forEach(::println) }
        //we can map the errors to a new type and continue the flow
        .mapLeft { errors -> errors.map { it.message } }
        //we handle the errors and that's it, to be used as the last invocation
        //also note that error is now a list of strings, as a result of the previous map
        .handleError { errors -> errors.forEach(::println) }

    /*
    Output:
        ValidationError(message='not an email' should be a valid email address)
        'not an email' should be a valid email address
    */

    val email: Validated<ValidationErrors, Email> = Email.valueOf("valid@email")
    email
        //just for debug
        .tap { println(it.value) }
        //we extract the value from the inline class
        .map { it.value }
        //now we have a Validated<ValidationErrors, String>
        .map { println(it) }

    /*
    Output:
        valid@email
        valid@email
    */
}