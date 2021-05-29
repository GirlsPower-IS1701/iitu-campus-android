package kz.iitu.campus.model.model

data class StudyPlan(
    val subject: String,
    val pk1: Float,
    val pk2: Float,
    val final: Float,
    val grade_letter: String,
    val gpa: Float
)

data class StudyPlanDto(
    val firstsemester: List<StudyPlan>,
    val secondsemester: List<StudyPlan>
)
