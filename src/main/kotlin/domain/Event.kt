package domain

import java.time.LocalDate

@JvmInline value class EventId(val value: Long)
@JvmInline value class Organizer(val value: String)
@JvmInline value class Title(val value: String)
@JvmInline value class Description(val value: String)

@JvmInline value class Url(val value: String)

@JvmInline value class City(val value: String)
@JvmInline value class Street(val value: String)
data class Address(val city: City, val street: Street)
enum class AgeRestriction(val description: String) {
    General("All ages admitted. Nothing that would offend parents for viewing by children."),
    PG("Some material may not be suitable for children. Parents urged to give \"parental guidance\""),
    PG13("Some material may be inappropriate for children under 13. Parents are urged to be cautious."),
    Restricted("Under 17 requires accompanying parent or adult guardian. Contains some adult material."),
    NC17("No One 17 and Under Admitted. Clearly adult.")
}
sealed class Event {
    abstract val id: EventId
    abstract val title: Title
    abstract val organizer: Organizer
    abstract val description: Description
    abstract val ageRestriction: AgeRestriction
    abstract val date: LocalDate

    data class Online(
        override val id: EventId,
        override val title: Title,
        override val organizer: Organizer,
        override val description: Description,
        override val ageRestriction: AgeRestriction,
        override val date: LocalDate,
        val url: Url
    ) : Event()

    data class AtAddress(
        override val id: EventId,
        override val title: Title,
        override val organizer: Organizer,
        override val description: Description,
        override val ageRestriction: AgeRestriction,
        override val date: LocalDate,
        val address: Address
    ) : Event()
}