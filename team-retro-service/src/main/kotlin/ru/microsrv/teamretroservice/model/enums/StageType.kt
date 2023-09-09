package ru.microsrv.teamretroservice.model.enums


/**
 * Этапы ретро.
 */
enum class StageType {

    /**
     * Что было хорошо.
     */
    WENT_WELL,

    /**
     * Что было плохо.
     */
    DIDNT_GO_WELL,

    /**
     * Что нужно сделать.
     */
    ACTION,

    /**
     * Кого похвалить.
     */
    KUDOS
}