package com.example.data

enum class UserRole(
    val label: String,
    val badgeTitle: String,
    val description: String,
    val permissionsSummary: String
) {
    PRINCIPAL(
        label = "Principal",
        badgeTitle = "School Leadership View",
        description = "School-wide competency trends, multi-class heatmaps, board alignment, and monthly growth tracking without excessive student ranking.",
        permissionsSummary = "School-wide Analytics • Cohort Heatmaps • Academic Planning • Audit Summaries"
    ),
    TEACHER(
        label = "Teacher",
        badgeTitle = "Science Educator View",
        description = "Class VIII-A diagnostic breakdown, misconception clusters, suggested 10-minute classroom interventions, and AI-assisted question review.",
        permissionsSummary = "Class Diagnostics • Misconception Engine • Intervention Planner • Question Bank Review"
    ),
    STUDENT(
        label = "Student",
        badgeTitle = "Learner Growth View",
        description = "Constructive competency profile showing strengths, areas to improve, and next learning targets rather than punitive rank tables.",
        permissionsSummary = "Competency Profile • Concept Feedback • Practice Targets • Monthly Progress"
    ),
    PARENT(
        label = "Parent",
        badgeTitle = "Family Support View",
        description = "Clear, jargon-free summary of what your child understands well, topics needing attention, and simple curiosity-building support at home.",
        permissionsSummary = "Plain-Language Summary • Strengths & Focus Areas • At-Home Support Guide"
    ),
    SCHOOL_ADMIN(
        label = "School Admin",
        badgeTitle = "Assessment Operations View",
        description = "Manage assessment delivery modes (Online, OMR/Paper, Computer Lab, Teacher-Assisted), class rosters, and role-based access control.",
        permissionsSummary = "Roster & Delivery Setup • OMR Batch Sync • Role Governance • Data Retention Controls"
    ),
    SUPER_ADMIN(
        label = "Super Admin",
        badgeTitle = "Platform Architecture View",
        description = "Multi-school tenant oversight, curriculum taxonomy mapping across CBSE/ICSE/State Boards, educator review queue, and psychometric roadmap.",
        permissionsSummary = "Multi-Tenant Schema • Taxonomy Governance • Educator AI Review Gate • Security Audit Logs"
    )
}

enum class WebsitePage(
    val title: String,
    val shortLabel: String,
    val category: String
) {
    HOME("Home", "Home", "Overview"),
    HOW_IT_WORKS("How It Works", "How It Works", "Platform"),
    ASSESSMENT_FRAMEWORK("Assessment Framework", "Framework", "Platform"),
    FOR_SCHOOLS("For Schools", "For Schools", "Solutions"),
    FOR_TEACHERS("For Teachers", "For Teachers", "Solutions"),
    STUDENTS_PARENTS("Students & Parents", "Students & Parents", "Solutions"),
    SAMPLE_REPORT("Sample Report & Dashboards", "Sample Report", "Analytics"),
    ABOUT("About ScienceLens", "About", "Company"),
    CONTACT_DEMO("Contact / Request Demo", "Request Demo", "Action"),
    FAQ("Frequently Asked Questions", "FAQ", "Resources"),
    PRIVACY("Privacy & Data Governance", "Privacy", "Legal"),
    TERMS("Terms of Use", "Terms", "Legal")
}

enum class CoreCompetency(
    val title: String,
    val shortName: String,
    val description: String,
    val exampleSkill: String
) {
    CONCEPTUAL_UNDERSTANDING(
        title = "Conceptual Understanding",
        shortName = "Conceptual",
        description = "Grasping foundational scientific principles, definitions, and cause-effect relationships beyond rote definitions.",
        exampleSkill = "Distinguishing between total force applied and force distributed per unit area."
    ),
    APPLICATION(
        title = "Application",
        shortName = "Application",
        description = "Transferring learned scientific concepts to unfamiliar everyday phenomena, tools, and engineering contexts.",
        exampleSkill = "Explaining why a pointed nail penetrates wood more easily than a blunt nail under identical force."
    ),
    INTERPRETATION(
        title = "Data & Graph Interpretation",
        shortName = "Interpretation",
        description = "Reading tables, experimental plots, ray diagrams, and multi-variable charts to extract valid patterns.",
        exampleSkill = "Identifying non-uniform motion or phase transitions from a temperature-time graph."
    ),
    SCIENTIFIC_REASONING(
        title = "Scientific Reasoning",
        shortName = "Reasoning",
        description = "Evaluating hypotheses, linking evidence to conclusions, and detecting logical flaws in experimental claims.",
        exampleSkill = "Predicting how changing resistor arrangement alters brightness in a parallel vs series circuit."
    ),
    PROBLEM_SOLVING(
        title = "Problem Solving",
        shortName = "Problem Solving",
        description = "Synthesizing multiple scientific rules and quantitative relationships to resolve multi-step scenarios.",
        exampleSkill = "Balancing competing variables in a buoyancy or chemical neutralization setup."
    ),
    SCIENTIFIC_INQUIRY(
        title = "Scientific Inquiry",
        shortName = "Inquiry",
        description = "Designing fair tests, isolating independent/dependent variables, and identifying sources of experimental error.",
        exampleSkill = "Spotting an uncontrolled variable in a classroom plant transpiration experiment."
    )
}

enum class QuestionTaxonomy(
    val label: String,
    val cognitiveFocus: String
) {
    CONCEPT_RECOGNITION("Concept Recognition", "Identify core scientific phenomena accurately in context"),
    CONCEPT_APPLICATION("Concept Application", "Apply laws and principles to practical situations"),
    INTERPRETATION("Interpretation", "Extract meaning from graphs, tables, and schematic diagrams"),
    REASONING("Reasoning", "Connect experimental evidence to scientific explanations"),
    PREDICTION("Prediction", "Forecast outcomes when system variables are modified"),
    ERROR_ANALYSIS("Error Analysis", "Detect procedural flaws or conceptual fallacies in an argument"),
    REAL_WORLD_APPLICATION("Real-World Application", "Analyze everyday tools, weather, health, and environmental systems"),
    SCIENTIFIC_INQUIRY("Scientific Inquiry", "Formulate testable hypotheses and control experimental variables")
}

data class School(
    val id: String,
    val name: String,
    val board: String,
    val classesCovered: String,
    val activeStudentsCount: Int,
    val deliveryModes: List<String>
)

data class QuestionOption(
    val letter: String,
    val text: String,
    val isCorrect: Boolean,
    val distractorRationale: String,
    val linkedMisconceptionTag: String?
)

data class ScienceQuestion(
    val id: String,
    val className: String,
    val board: String,
    val chapter: String,
    val topic: String,
    val concept: String,
    val competency: CoreCompetency,
    val taxonomy: QuestionTaxonomy,
    val cognitiveLevel: String,
    val difficulty: String,
    val questionFormat: String,
    val contextDescription: String,
    val stem: String,
    val options: List<QuestionOption>,
    val likelyMisconception: String,
    val evidencePattern: String,
    val suggestedIntervention: String,
    val recommendedPracticePrompts: List<String>
)

data class MisconceptionCase(
    val id: String,
    val classLevel: String,
    val chapter: String,
    val conceptInvolved: String,
    val studentSymptom: String,
    val likelyMisconception: String,
    val evidenceFromResponses: String,
    val affectedStudentsPercent: Int,
    val suggestedTeacherIntervention: String,
    val interventionSteps: List<String>,
    val recommendedPracticeQuestions: List<String>
)

data class TopicAttentionItem(
    val topic: String,
    val chapter: String,
    val competencyScore: Int,
    val primaryGap: String,
    val commonMisconception: String
)

data class TeacherDashboardData(
    val className: String,
    val cycleLabel: String,
    val overallCompetency: Int,
    val conceptualUnderstanding: Int,
    val application: Int,
    val scientificReasoning: Int,
    val dataInterpretation: Int,
    val topicsNeedingAttention: List<TopicAttentionItem>,
    val coreInsight: String,
    val suggestedInterventionSummary: String,
    val interventionComponents: List<Pair<String, String>>
)

data class MonthlyTrendPoint(
    val month: String,
    val schoolCompetency: Int,
    val conceptualScore: Int,
    val applicationScore: Int
)

data class HeatmapRow(
    val topicDomain: String,
    val classVI: Int,
    val classVII: Int,
    val classVIII: Int,
    val classIX: Int,
    val classX: Int
)

data class ClassComparisonItem(
    val sectionName: String,
    val overallScore: Int,
    val applicationScore: Int,
    val reasoningScore: Int,
    val participationPercent: Int
)

data class PrincipalDashboardData(
    val schoolCompetency: Int,
    val previousMonthCompetency: Int,
    val changePoints: Int,
    val participationRate: Float,
    val assessmentsCompletedCount: Int,
    val trendSeries: List<MonthlyTrendPoint>,
    val topicHeatmap: List<HeatmapRow>,
    val classComparisons: List<ClassComparisonItem>
)

data class StudentReportData(
    val studentAlias: String,
    val className: String,
    val assessmentMonth: String,
    val conceptualUnderstanding: Int,
    val application: Int,
    val scientificReasoning: Int,
    val dataInterpretation: Int,
    val strengths: List<String>,
    val areasToImprove: List<String>,
    val nextLearningTarget: String,
    val recommendedActivities: List<String>
)

data class ParentReportData(
    val childAlias: String,
    val className: String,
    val summaryHeadline: String,
    val whatYourChildDoesWell: List<String>,
    val areasRequiringAttention: List<String>,
    val suggestedSupportAtHome: List<String>,
    val teacherNote: String
)

data class DeliveryModeInfo(
    val title: String,
    val subtitle: String,
    val infrastructureRequirement: String,
    val howItWorks: String,
    val bestFor: String
)

data class PricingTier(
    val id: String,
    val title: String,
    val subtitle: String,
    val targetScope: String,
    val highlights: List<String>,
    val ctaLabel: String,
    val isFeatured: Boolean = false
)

data class FaqItem(
    val category: String,
    val question: String,
    val answer: String
)
