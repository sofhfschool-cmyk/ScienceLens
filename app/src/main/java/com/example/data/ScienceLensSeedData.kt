package com.example.data

object ScienceLensSeedData {

    val sampleQuestions: List<ScienceQuestion> = listOf(
        ScienceQuestion(
            id = "SL-VIII-FP-01",
            className = "Class VIII",
            board = "CBSE / ICSE / State Boards",
            chapter = "Force and Pressure",
            topic = "Pressure and Contact Area",
            concept = "Inverse relationship between pressure and area of contact (P = F / A)",
            competency = CoreCompetency.APPLICATION,
            taxonomy = QuestionTaxonomy.CONCEPT_APPLICATION,
            cognitiveLevel = "Apply & Analyze",
            difficulty = "Medium Difficulty",
            questionFormat = "Application-Based MCQ",
            contextDescription = "Real-world carpentry & everyday mechanics scenario examining how contact area alters pressure under constant force.",
            stem = "A nail is pressed into a wooden surface using the same force. Which end produces greater pressure?",
            options = listOf(
                QuestionOption(
                    letter = "A",
                    text = "Flat end",
                    isCorrect = false,
                    distractorRationale = "Associates larger visible surface area with greater 'push' or impact, inverting the area-pressure relationship.",
                    linkedMisconceptionTag = "Direct Area-Pressure Fallacy"
                ),
                QuestionOption(
                    letter = "B",
                    text = "Pointed end",
                    isCorrect = true,
                    distractorRationale = "Correct understanding: Pressure = Force / Area. Concentrating the same force over a tiny tip area produces significantly higher pressure.",
                    linkedMisconceptionTag = null
                ),
                QuestionOption(
                    letter = "C",
                    text = "Both produce equal pressure",
                    isCorrect = false,
                    distractorRationale = "Confuses Force with Pressure: assumes that because applied force is identical, the resulting pressure must also be identical.",
                    linkedMisconceptionTag = "Force-Pressure Equivalence Misconception"
                ),
                QuestionOption(
                    letter = "D",
                    text = "Depends only on nail length",
                    isCorrect = false,
                    distractorRationale = "Focuses on an irrelevant physical dimension (length of the shaft) rather than the area of the surface in contact with wood.",
                    linkedMisconceptionTag = "Irrelevant Variable Distraction"
                )
            ),
            likelyMisconception = "Student equates Force with Pressure (assuming equal force always yields equal effect regardless of contact area).",
            evidencePattern = "Selecting Option C on nail/knife-edge items while correctly recalling the formula P = F/A in direct definition questions.",
            suggestedIntervention = "10-minute classroom demonstration pressing a blunt pencil vs sharpened pencil into modelling clay with equal hand push + 3 application prompts + exit ticket.",
            recommendedPracticePrompts = listOf(
                "Why do heavy army tanks move on wide steel caterpillar tracks instead of ordinary tyres?",
                "Compare the pressure exerted on sand by a camel's broad foot versus a horse's narrower hoof of similar body weight.",
                "If a brick is placed upright vs flat on soft foam, how does the depth of depression change?"
            )
        ),
        ScienceQuestion(
            id = "SL-VIII-CE-02",
            className = "Class VIII",
            board = "CBSE / ICSE",
            chapter = "Chemical Effects of Electric Current",
            topic = "Conduction in Liquids",
            concept = "Electrolytic dissociation and mobile ions in aqueous solutions",
            competency = CoreCompetency.SCIENTIFIC_REASONING,
            taxonomy = QuestionTaxonomy.SCIENTIFIC_INQUIRY,
            cognitiveLevel = "Evaluate Experimental Setup",
            difficulty = "Medium Difficulty",
            questionFormat = "Experimental Situation",
            contextDescription = "Laboratory LED tester circuit comparing distilled water and rainwater.",
            stem = "In a school lab circuit with a battery and LED tester, the LED does not glow when electrodes are dipped in distilled water, but glows dimly when a pinch of common salt is dissolved. What is the best scientific explanation?",
            options = listOf(
                QuestionOption(
                    letter = "A",
                    text = "Salt increases the temperature of water, generating electricity",
                    isCorrect = false,
                    distractorRationale = "Confuses thermal energy changes during dissolution with charge carrier generation.",
                    linkedMisconceptionTag = "Thermal-Electrical Confusion"
                ),
                QuestionOption(
                    letter = "B",
                    text = "Dissolved salt provides free mobile ions (Na+ and Cl-) that carry charge through the liquid",
                    isCorrect = true,
                    distractorRationale = "Correct reasoning: Pure water lacks sufficient free ions; dissociating NaCl introduces mobile ions enabling current flow.",
                    linkedMisconceptionTag = null
                ),
                QuestionOption(
                    letter = "C",
                    text = "Salt particles form a solid metallic bridge between the two electrodes",
                    isCorrect = false,
                    distractorRationale = "Applies metallic wire conduction models directly to liquid electrolytes.",
                    linkedMisconceptionTag = "Solid-Bridge Conduction Model"
                ),
                QuestionOption(
                    letter = "D",
                    text = "Distilled water absorbs all electrons from the battery permanently",
                    isCorrect = false,
                    distractorRationale = "Misunderstands closed-loop current flow as electron consumption.",
                    linkedMisconceptionTag = "Current Consumption Misconception"
                )
            ),
            likelyMisconception = "Student models liquid conduction as solid particle chains rather than mobile ion migration.",
            evidencePattern = "Difficulty predicting whether sugar solution vs lemon juice will conduct electricity in unfamiliar setups.",
            suggestedIntervention = "Compare salt water, lemon juice, and sugar solution using a magnetic compass needle / LED tester and map ion dissociation visually.",
            recommendedPracticePrompts = listOf(
                "Why does distilled water fail to conduct electricity while tap water conducts weakly?",
                "Predict whether a solution of glucose (which dissolves without forming ions) will make the LED glow."
            )
        ),
        ScienceQuestion(
            id = "SL-VIII-LT-03",
            className = "Class VIII",
            board = "CBSE / ICSE / State Boards",
            chapter = "Light",
            topic = "Laws of Reflection",
            concept = "Angle of incidence is measured from the normal, not the mirror surface",
            competency = CoreCompetency.INTERPRETATION,
            taxonomy = QuestionTaxonomy.INTERPRETATION,
            cognitiveLevel = "Diagram & Data Interpretation",
            difficulty = "High Difficulty",
            questionFormat = "Diagram-Based Question",
            contextDescription = "Optical ray diagram where the glancing angle with the mirror surface is given as 30 degrees.",
            stem = "A ray of light strikes a plane mirror making an angle of 30° with the mirror surface. What is the angle of reflection?",
            options = listOf(
                QuestionOption(
                    letter = "A",
                    text = "30°",
                    isCorrect = false,
                    distractorRationale = "Directly copies the angle given in the stem without subtracting from 90° to find the angle with the normal.",
                    linkedMisconceptionTag = "Surface-Angle vs Normal-Angle Confusion"
                ),
                QuestionOption(
                    letter = "B",
                    text = "60°",
                    isCorrect = true,
                    distractorRationale = "Correct interpretation: Normal is 90° to the surface, so angle of incidence = 90° - 30° = 60°, and angle of reflection = 60°.",
                    linkedMisconceptionTag = null
                ),
                QuestionOption(
                    letter = "C",
                    text = "90°",
                    isCorrect = false,
                    distractorRationale = "Confuses the position of the normal line with the reflected ray angle.",
                    linkedMisconceptionTag = "Normal Line Angle Confusion"
                ),
                QuestionOption(
                    letter = "D",
                    text = "120°",
                    isCorrect = false,
                    distractorRationale = "Calculates total angle between incident and reflected rays (60° + 60°) instead of the angle of reflection.",
                    linkedMisconceptionTag = "Total Deviation vs Reflection Angle"
                )
            ),
            likelyMisconception = "Student measures the angle of incidence between the incident ray and the mirror surface instead of the perpendicular normal.",
            evidencePattern = "Selects 30° (Option A) despite knowing the statement 'Angle of incidence equals angle of reflection'.",
            suggestedIntervention = "Have students draw the dashed normal line first in red ink before measuring any ray angles with a protractor.",
            recommendedPracticePrompts = listOf(
                "If a light ray strikes a plane mirror perpendicular (along the normal), what is the angle of incidence?",
                "What is the total angle between the incident ray and reflected ray when the ray makes 40° with the mirror surface?"
            )
        ),
        ScienceQuestion(
            id = "SL-IX-MT-04",
            className = "Class IX",
            board = "CBSE / ICSE",
            chapter = "Motion",
            topic = "Velocity-Time Graph Analysis",
            concept = "Slope of v-t graph represents acceleration; horizontal line means zero acceleration",
            competency = CoreCompetency.INTERPRETATION,
            taxonomy = QuestionTaxonomy.ERROR_ANALYSIS,
            cognitiveLevel = "Graph & Data Interpretation",
            difficulty = "Medium Difficulty",
            questionFormat = "Graph / Data Interpretation",
            contextDescription = "Comparing a distance-time graph and a velocity-time graph with identical horizontal straight lines.",
            stem = "On a velocity-time (v-t) graph, a car's motion is represented by a straight horizontal line parallel to the time axis at v = 15 m/s. Which statement accurately describes the car?",
            options = listOf(
                QuestionOption(
                    letter = "A",
                    text = "The car is stationary (at rest)",
                    isCorrect = false,
                    distractorRationale = "Confuses a velocity-time graph with a distance-time graph where a horizontal line means no change in position.",
                    linkedMisconceptionTag = "d-t vs v-t Graph Axis Confusion"
                ),
                QuestionOption(
                    letter = "B",
                    text = "The car is moving with constant velocity and zero acceleration",
                    isCorrect = true,
                    distractorRationale = "Correct interpretation: Velocity remains steady at 15 m/s over time, meaning acceleration is 0 m/s².",
                    linkedMisconceptionTag = null
                ),
                QuestionOption(
                    letter = "C",
                    text = "The car is moving with uniform positive acceleration of 15 m/s²",
                    isCorrect = false,
                    distractorRationale = "Equates a non-zero velocity value with non-zero acceleration.",
                    linkedMisconceptionTag = "Velocity-Acceleration Conflation"
                ),
                QuestionOption(
                    letter = "D",
                    text = "The car is moving along a flat horizontal road only",
                    isCorrect = false,
                    distractorRationale = "Treats the graph line literally as a picture of the physical road terrain.",
                    linkedMisconceptionTag = "Graph-as-Picture Misconception"
                )
            ),
            likelyMisconception = "Student reads a velocity-time graph as if it were a position-time graph (or a literal hill/road profile).",
            evidencePattern = "Consistently marks horizontal segments on v-t graphs as 'object at rest'.",
            suggestedIntervention = "Side-by-side comparison card activity matching physical motion stories to both (x-t) and (v-t) plots.",
            recommendedPracticePrompts = listOf(
                "Sketch both the distance-time and velocity-time graphs for a cyclist pedaling at a steady 5 m/s.",
                "How do you calculate the distance traveled between t = 2s and t = 6s from the horizontal v-t graph?"
            )
        ),
        ScienceQuestion(
            id = "SL-VII-LP-05",
            className = "Class VII",
            board = "CBSE / ICSE / State Boards",
            chapter = "Respiration in Organisms",
            topic = "Plant Gas Exchange Caselet",
            concept = "Plants respire continuously day and night; photosynthesis occurs only in light",
            competency = CoreCompetency.CONCEPTUAL_UNDERSTANDING,
            taxonomy = QuestionTaxonomy.REAL_WORLD_APPLICATION,
            cognitiveLevel = "Caselet Reasoning",
            difficulty = "Medium Difficulty",
            questionFormat = "Caselet",
            contextDescription = "Greenhouse sensor caselet measuring oxygen and carbon dioxide levels around potted plants across a 24-hour cycle.",
            stem = "Caselet: A school science club measures gas levels in a sealed transparent chamber with a green plant. Which statement correctly describes when cellular respiration and photosynthesis take place in the plant?",
            options = listOf(
                QuestionOption(
                    letter = "A",
                    text = "Photosynthesis occurs during the day; respiration occurs only at night",
                    isCorrect = false,
                    distractorRationale = "Very common textbook misconception that plants 'switch' from photosynthesis by day to respiration only at night.",
                    linkedMisconceptionTag = "Day-Night Exclusive Process Fallacy"
                ),
                QuestionOption(
                    letter = "B",
                    text = "Respiration occurs continuously day and night; photosynthesis occurs in the presence of light",
                    isCorrect = true,
                    distractorRationale = "Correct understanding: Living plant cells require ATP continuously via respiration; daytime net O2 release occurs because photosynthesis rate exceeds respiration rate.",
                    linkedMisconceptionTag = null
                ),
                QuestionOption(
                    letter = "C",
                    text = "Plants only perform photosynthesis and do not respire like animals",
                    isCorrect = false,
                    distractorRationale = "Treats photosynthesis as the plant equivalent of respiration rather than a food-synthesis process.",
                    linkedMisconceptionTag = "Photosynthesis-as-Respiration Fallacy"
                ),
                QuestionOption(
                    letter = "D",
                    text = "Both photosynthesis and respiration occur only in sunlight",
                    isCorrect = false,
                    distractorRationale = "Assumes all plant metabolic activities stop completely in darkness.",
                    linkedMisconceptionTag = "Light-Dependent Metabolism Assumption"
                )
            ),
            likelyMisconception = "Student believes plants respire only at night and photosynthesize during the day.",
            evidencePattern = "High error rate across Classes VII–X when asked about daytime mitochondrial respiration in leaf cells.",
            suggestedIntervention = "Compare net gas exchange arrows vs simultaneous cellular processes using a two-column energy ledger (Making Food vs Using Food).",
            recommendedPracticePrompts = listOf(
                "Why do germinating seeds kept in a dark flask release carbon dioxide and heat?",
                "During bright midday sunlight, why is no net CO2 release detected from a leaf even though it is respiring?"
            )
        )
    )

    val misconceptionCases: List<MisconceptionCase> = listOf(
        MisconceptionCase(
            id = "MC-01",
            classLevel = "Class VIII",
            chapter = "Force and Pressure",
            conceptInvolved = "Pressure as Force per Unit Area (P = F / A)",
            studentSymptom = "A student repeatedly confuses force and pressure when contact area changes.",
            likelyMisconception = "Believes equal applied force always produces equal pressure regardless of surface contact area, or assumes larger surface area exerts more pressure.",
            evidenceFromResponses = "Answered Q4 (Nail tip vs flat head), Q12 (School bag strap width), and Q19 (Liquid column base area) by selecting 'Equal force = Equal pressure' distractors across 3 consecutive items.",
            affectedStudentsPercent = 34,
            suggestedTeacherIntervention = "Conduct a 10-minute tactile clay-and-block classroom demonstration contrasting total push (Force) with concentration of push (Pressure), followed by 3 guided application questions.",
            interventionSteps = listOf(
                "10-Min Demo: Press an identical 1 kg weight onto soft clay using a broad coin vs a narrow dowel.",
                "3 Application Questions: Nail tip, snowshoes vs boots, and broad dam walls.",
                "Discussion Prompt: 'Can a small force ever create a larger pressure than a huge force?'",
                "Exit Ticket: 2-minute sketch explaining why cutting tools have sharp edges."
            ),
            recommendedPracticeQuestions = listOf(
                "Q-FP-104: A girl stands on soft sand on one foot vs both feet. How does her weight (force) and pressure on the sand change?",
                "Q-FP-109: Two blocks of identical mass (2 kg) have base areas of 10 cm² and 40 cm². Which exerts greater pressure on the table?",
                "Q-FP-115: Why do porters place a round cloth turban (pagri) on their heads when carrying heavy loads?"
            )
        ),
        MisconceptionCase(
            id = "MC-02",
            classLevel = "Class VIII",
            chapter = "Chemical Effects of Electric Current",
            conceptInvolved = "Electrolytic Conduction via Mobile Ions",
            studentSymptom = "Struggles to predict which household liquids conduct electricity.",
            likelyMisconception = "Assumes any clear liquid or any dissolved substance (such as sugar) conducts electricity the same way a copper wire does.",
            evidenceFromResponses = "Selected 'Sugar solution conducts brightly' in Q8 and 'Electrons swim freely through pure distilled water' in Q15.",
            affectedStudentsPercent = 29,
            suggestedTeacherIntervention = "Contrast ionic dissociation (NaCl, lemon juice) against molecular dissolution (sugar, distilled water) using a particle card sort and LED tester.",
            interventionSteps = listOf(
                "10-Min Demo: Live LED/compass tester check across distilled water, salt water, and sugar water.",
                "3 Application Questions: Wet-hand electrical safety, electroplating spoon setup, rainwater vs pure water.",
                "Discussion Prompt: 'What invisible carriers move inside the liquid when the bulb glows?'",
                "Exit Ticket: Classify 4 solutions into Ionic Conductors vs Poor Conductors."
            ),
            recommendedPracticeQuestions = listOf(
                "Q-CE-202: Why should electrical appliances never be touched with wet hands even though pure water is a poor conductor?",
                "Q-CE-208: In copper electroplating, why does the copper sulphate solution concentration remain constant when a copper anode is used?"
            )
        ),
        MisconceptionCase(
            id = "MC-03",
            classLevel = "Class VIII",
            chapter = "Light",
            conceptInvolved = "Measuring Angles of Incidence & Reflection from the Normal",
            studentSymptom = "Consistently miscalculates ray angles when given the angle with the mirror surface.",
            likelyMisconception = "Measures the angle of incidence between the ray and the mirror surface rather than between the ray and the perpendicular normal.",
            evidenceFromResponses = "Selected 30° instead of 60° on ray-diagram item Q22 and misidentified normal reflection as 90° instead of 0° on Q25.",
            affectedStudentsPercent = 31,
            suggestedTeacherIntervention = "Enforce the 'Draw the Normal First' protocol in ray diagrams before reading any numerical angle.",
            interventionSteps = listOf(
                "10-Min Demo: Laser pointer + protractor board with a bold perpendicular rod marking the Normal.",
                "3 Application Questions: Glancing angle conversion, periscope double reflection, normal incidence.",
                "Discussion Prompt: 'Why do physicists measure angles from the normal instead of the surface?'",
                "Exit Ticket: Find angle of reflection when incident ray makes 25° with the mirror."
            ),
            recommendedPracticeQuestions = listOf(
                "Q-LT-301: A ray of light strikes a mirror along the normal. What are the angles of incidence and reflection?",
                "Q-LT-305: Two plane mirrors are placed at 90° to each other. Trace a ray striking the first mirror at 35° to its surface."
            )
        )
    )

    val teacherDashboardClass8A = TeacherDashboardData(
        className = "Class VIII-A (Demonstration Data)",
        cycleLabel = "Monthly Assessment Cycle • October",
        overallCompetency = 68,
        conceptualUnderstanding = 74,
        application = 61,
        scientificReasoning = 58,
        dataInterpretation = 67,
        topicsNeedingAttention = listOf(
            TopicAttentionItem(
                topic = "Force and Pressure",
                chapter = "Chapter 8 • Physics",
                competencyScore = 56,
                primaryGap = "Application & Quantitative Reasoning",
                commonMisconception = "34% equate equal force with equal pressure regardless of contact area."
            ),
            TopicAttentionItem(
                topic = "Chemical Effects of Electric Current",
                chapter = "Chapter 11 • Chemistry",
                competencyScore = 59,
                primaryGap = "Experimental Inquiry & Ion Model",
                commonMisconception = "29% assume dissolved sugar conducts current like dissolved salt."
            ),
            TopicAttentionItem(
                topic = "Light",
                chapter = "Chapter 13 • Physics",
                competencyScore = 62,
                primaryGap = "Ray Diagram Interpretation",
                commonMisconception = "31% measure angle of incidence from mirror surface instead of the normal."
            )
        ),
        coreInsight = "Students show reasonable recall (74% Conceptual Understanding) but struggle to apply concepts to unfamiliar situations (61% Application, 58% Scientific Reasoning).",
        suggestedInterventionSummary = "10-minute demonstration + 3 application questions + discussion prompt + exit ticket.",
        interventionComponents = listOf(
            "STEP_1_DEMO" to "10-Minute Demonstration: Tactile comparison of force vs pressure using blunt and pointed rods on modelling clay.",
            "STEP_2_QUESTIONS" to "3 Application Questions: Guided class walkthrough on unfamiliar real-world contexts (hydraulic lifts, snowshoes, electrolyte testers).",
            "STEP_3_DISCUSSION" to "Peer Discussion Prompt: Structured 3-minute 'Think-Pair-Share' asking students to defend why a distractor is tempting but scientifically flawed.",
            "STEP_4_EXIT_TICKET" to "2-Minute Diagnostic Exit Ticket: Quick 2-item check before next month's reassessment cycle."
        )
    )

    val principalDashboard = PrincipalDashboardData(
        schoolCompetency = 68,
        previousMonthCompetency = 63,
        changePoints = 5,
        participationRate = 96.4f,
        assessmentsCompletedCount = 1420,
        trendSeries = listOf(
            MonthlyTrendPoint("Jun", 56, 65, 51),
            MonthlyTrendPoint("Jul", 59, 68, 54),
            MonthlyTrendPoint("Aug", 61, 70, 56),
            MonthlyTrendPoint("Sep", 63, 72, 58),
            MonthlyTrendPoint("Oct", 68, 75, 63)
        ),
        topicHeatmap = listOf(
            HeatmapRow("Force, Motion & Pressure", 72, 70, 58, 66, 71),
            HeatmapRow("Light, Optics & Waves", 75, 68, 62, 69, 67),
            HeatmapRow("Chemical Reactions & Ions", 69, 66, 59, 64, 70),
            HeatmapRow("Life Processes & Ecology", 78, 74, 73, 71, 76),
            HeatmapRow("Experimental Inquiry & Graphs", 64, 62, 61, 63, 68)
        ),
        classComparisons = listOf(
            ClassComparisonItem("Class VI (All Sections)", 71, 66, 64, 98),
            ClassComparisonItem("Class VII (All Sections)", 69, 64, 62, 97),
            ClassComparisonItem("Class VIII-A", 68, 61, 58, 96),
            ClassComparisonItem("Class VIII-B", 66, 60, 59, 95),
            ClassComparisonItem("Class IX (All Sections)", 67, 62, 61, 96),
            ClassComparisonItem("Class X (All Sections)", 70, 66, 65, 97)
        )
    )

    val studentReport = StudentReportData(
        studentAlias = "Aarav M. (Demonstration Profile)",
        className = "Class VIII-A",
        assessmentMonth = "October Monthly Assessment",
        conceptualUnderstanding = 78,
        application = 62,
        scientificReasoning = 60,
        dataInterpretation = 71,
        strengths = listOf(
            "Strong grasp of core scientific definitions and laws in Force, Friction, and Sound.",
            "Accurately interprets tabular experimental data and bar charts (71% Data Interpretation).",
            "Correctly identifies factors affecting friction on different surfaces."
        ),
        areasToImprove = listOf(
            "Applying the Pressure formula (P = F/A) when contact area changes in real-world tools.",
            "Measuring incident and reflected light ray angles relative to the normal rather than the mirror surface.",
            "Distinguishing between ionic solutions and molecular solutions during liquid conduction experiments."
        ),
        nextLearningTarget = "Practice transferring classroom formulas to unfamiliar everyday situations by sketching free-body and ray diagrams before choosing an answer.",
        recommendedActivities = listOf(
            "Complete the 3-item 'Force vs Pressure in Everyday Tools' diagnostic practice set.",
            "Always draw the 90° Normal line first when solving plane mirror reflection problems.",
            "Review why rainwater conducts electricity while pure distilled water does not."
        )
    )

    val parentReport = ParentReportData(
        childAlias = "Aarav M. (Demonstration Profile)",
        className = "Class VIII-A • October Science Summary",
        summaryHeadline = "Aarav understands core Science concepts well and is now building confidence in applying them to practical, everyday situations.",
        whatYourChildDoesWell = listOf(
            "Understands foundational classroom concepts clearly and remembers key scientific ideas.",
            "Reads charts, tables, and simple experimental observations accurately.",
            "Participates consistently in monthly school assessments."
        ),
        areasRequiringAttention = listOf(
            "Connecting textbook rules to real-life examples (such as how sharp tools or wide straps change pressure).",
            "Looking closely at diagrams in Light and Electricity questions before picking an option."
        ),
        suggestedSupportAtHome = listOf(
            "Ask 'Why does this work?' during everyday moments — for example, why a wide backpack strap feels more comfortable than a thin string.",
            "Encourage your child to draw a quick sketch when revising Science chapters instead of only memorizing definitions.",
            "Focus on curiosity and reasoning rather than only test scores — mistakes help reveal which idea needs a second look."
        ),
        teacherNote = "This report highlights learning patterns to support classroom teaching and home encouragement. No student rank is assigned."
    )

    val deliveryModes: List<DeliveryModeInfo> = listOf(
        DeliveryModeInfo(
            title = "Online Assessment Mode",
            subtitle = "Browser & Tablet Ready",
            infrastructureRequirement = "School tablets, Chromebooks, or student devices with standard connectivity",
            howItWorks = "Students take the 30-question, 45-minute monthly assessment directly with rich diagrams, caselets, and instant submission into the diagnostic engine.",
            bestFor = "Schools with 1:1 device programs or scheduled digital assessment windows."
        ),
        DeliveryModeInfo(
            title = "OMR / Paper Assessment Mode",
            subtitle = "Zero Student Device Dependency",
            infrastructureRequirement = "Printed question booklets + standard OMR response sheets scanned via teacher phone or school scanner",
            howItWorks = "Schools print curated monthly ScienceLens papers. Completed OMR sheets are batch-scanned to generate the exact same misconception and competency dashboards.",
            bestFor = "Schools preferring pen-and-paper exam routines or operating with limited digital infrastructure."
        ),
        DeliveryModeInfo(
            title = "Computer Lab Rotation Mode",
            subtitle = "Shared Infrastructure Scheduling",
            infrastructureRequirement = "Single school computer lab (25–40 desktops) across staggered periods",
            howItWorks = "Sections rotate through the school computer lab using equivalent curriculum-aligned question forms to maintain assessment integrity.",
            bestFor = "Schools with a central IT lab serving Classes VI–X across a 2–3 day monthly window."
        ),
        DeliveryModeInfo(
            title = "Teacher-Assisted Classroom Mode",
            subtitle = "Smartboard / Projector + Response Capture",
            infrastructureRequirement = "Classroom projector/IFP or teacher tablet + quick response capture",
            howItWorks = "Teachers project diagram/caselet items for whole-class diagnostic checkpoints or enter responses for low-connectivity sections.",
            bestFor = "Formative remediation checks and schools scaling technology gradually."
        )
    )

    val pricingTiers: List<PricingTier> = listOf(
        PricingTier(
            id = "PILOT",
            title = "Pilot Program",
            subtitle = "Experience 1–2 Monthly Assessment Cycles",
            targetScope = "Selected classes or a single middle-school grade band (e.g., Classes VII–VIII)",
            highlights = listOf(
                "1–2 complete monthly competency-based Science assessments (30 items, 45 mins)",
                "Full Misconception Engine diagnostics for participating sections",
                "Teacher Dashboard + Principal Summary Report + Student/Parent samples",
                "Support for Online, Computer Lab, or OMR/Paper delivery",
                "Guided academic coordinator walkthrough of diagnostic findings"
            ),
            ctaLabel = "Request Pilot Details"
        ),
        PricingTier(
            id = "SCHOOL_PLAN",
            title = "School Plan",
            subtitle = "Full Academic Year Continuous Learning Cycle",
            targetScope = "Complete school implementation across Classes VI–X",
            highlights = listOf(
                "Monthly Teach → Assess → Diagnose → Remediate → Reassess cycles",
                "Curriculum mapping aligned to your school's board (CBSE, ICSE, or State Board)",
                "Complete Teacher, Principal, Student, and Parent reporting suite",
                "Targeted 10-minute classroom intervention packs & practice items",
                "Month-by-month competency growth tracking & topic heatmaps",
                "Role-based access for Principal, Coordinators, Teachers, Students & Parents"
            ),
            ctaLabel = "Request School Pricing",
            isFeatured = true
        ),
        PricingTier(
            id = "INSTITUTIONAL",
            title = "Institutional Plan",
            subtitle = "Multi-Branch School Groups & Trusts",
            targetScope = "School networks, educational societies, and multi-campus institutions",
            highlights = listOf(
                "Everything in School Plan across all member campuses",
                "Central Academic Director & Trust-level comparative competency analytics",
                "Custom academic calendar scheduling and hybrid OMR + Online workflows",
                "Dedicated educator onboarding workshops & assessment coordinator support",
                "Centralized governance, audit logs, and custom data retention policies"
            ),
            ctaLabel = "Request Institutional Consultation"
        )
    )

    val initialAiDrafts: List<AiDraftQuestionEntity> = listOf(
        AiDraftQuestionEntity(
            className = "Class VIII",
            board = "CBSE",
            chapter = "Friction",
            topic = "Static vs Sliding Friction",
            competency = "Scientific Reasoning",
            taxonomy = "Prediction",
            estimatedDifficulty = "Medium (Est. p-value: 0.58)",
            questionStem = "A heavy wooden crate requires a 120 N horizontal push just to start moving across a floor, but only 95 N to keep sliding at constant speed. Why is the force needed to maintain motion lower?",
            correctOption = "Interlocking of microscopic surface irregularities has less time to settle once surfaces are already sliding past each other.",
            targetMisconception = "Believes friction disappears once an object starts moving, or thinks the crate loses mass while moving.",
            reviewStatus = "PENDING_EDUCATOR_REVIEW",
            reviewerNote = "AI-generated draft tagged with distractor rationale. Awaiting senior Science educator verification before entering active school bank."
        ),
        AiDraftQuestionEntity(
            className = "Class IX",
            board = "ICSE",
            chapter = "Fluids & Upthrust",
            topic = "Archimedes' Principle",
            competency = "Application",
            taxonomy = "Error Analysis",
            estimatedDifficulty = "High (Est. p-value: 0.46)",
            questionStem = "Two solid spheres of identical volume—one made of aluminium and one of lead—are completely submerged in water. Which sphere experiences a greater buoyant upthrust?",
            correctOption = "Both experience the exact same upthrust because they displace equal volumes of water.",
            targetMisconception = "Assumes heavier objects experience greater buoyant force (confusing weight with volume of displaced fluid).",
            reviewStatus = "APPROVED_FOR_BANK",
            reviewerNote = "Verified by Academic Review Panel: Distractor C accurately isolates mass-vs-volume buoyancy misconception."
        ),
        AiDraftQuestionEntity(
            className = "Class VII",
            board = "State Board",
            chapter = "Heat and Temperature",
            topic = "Thermal Equilibrium & Conductors",
            competency = "Conceptual Understanding",
            taxonomy = "Real-World Application",
            estimatedDifficulty = "Medium (Est. p-value: 0.62)",
            questionStem = "On a cold morning, a metal park bench feels much colder to touch than a wooden bench right next to it. How do their actual temperatures compare?",
            correctOption = "Both benches are at approximately the same surrounding temperature; metal conducts heat away from your hand much faster.",
            targetMisconception = "Equates thermal sensation ('feels colder') with lower thermometer reading.",
            reviewStatus = "PENDING_EDUCATOR_REVIEW",
            reviewerNote = "AI tagged for Concept Application. Needs minor wording check for Class VII vocabulary level."
        )
    )

    val faqs: List<FaqItem> = listOf(
        FaqItem(
            category = "Pedagogy & Purpose",
            question = "How is ScienceLens different from regular school unit tests or coaching tests?",
            answer = "Regular tests primarily report total marks or percentages, which often reflect memorization. ScienceLens designs every 30-question monthly assessment around 6 core scientific competencies and carefully engineered distractors. When a student chooses an incorrect option, ScienceLens identifies the specific underlying misconception and gives the teacher a practical 10-minute classroom intervention."
        ),
        FaqItem(
            category = "Curriculum & Boards",
            question = "Does ScienceLens align with CBSE, ICSE, and State Board syllabi?",
            answer = "Yes. Questions are mapped to the chapter and topic sequences of CBSE, ICSE, and selected State Boards for Classes VI–X while maintaining a common underlying Science competency framework. Schools select their board and chapter schedule so monthly assessments match what has actually been taught in class."
        ),
        FaqItem(
            category = "Delivery & Infrastructure",
            question = "Can our school use ScienceLens if we do not have devices for every student?",
            answer = "Absolutely. ScienceLens supports four flexible delivery modes: Online Assessment, Computer Lab rotation, OMR/Paper assessment (printed papers with quick OMR sheet scanning), and Teacher-Assisted classroom mode. Every delivery mode produces the exact same diagnostic dashboards and reports."
        ),
        FaqItem(
            category = "Diagnostics & AI Governance",
            question = "Are misconception insights definitive diagnoses? How is AI used?",
            answer = "Misconception insights are diagnostic indicators designed for teacher review and classroom inquiry—never definitive labels on a child. AI is used as an assistive layer for question tagging, distractor design, and report summarization. Every AI-assisted question draft must pass human educator review before publication."
        ),
        FaqItem(
            category = "Student Well-Being & Privacy",
            question = "Do you rank students publicly or train external AI models on student data?",
            answer = "No. ScienceLens avoids excessive student ranking and focuses on learning patterns, strengths, and next learning targets. We collect minimal student data, enforce strict role-based access and encryption, never publish student information publicly, and never use school or student data to train external AI models without explicit institutional authorization."
        ),
        FaqItem(
            category = "Pricing & Implementation",
            question = "How is pricing structured for schools?",
            answer = "Pricing depends on school size, assessment frequency, and selected delivery/support services. Schools can begin with a focused Pilot Program, adopt the full-year School Plan (Classes VI–X), or configure an Institutional Plan for multi-branch school groups."
        )
    )
}
