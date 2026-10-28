package com.dcdevelopers.cybersaathi.data.repository

import com.dcdevelopers.cybersaathi.data.models.*

object ContentRepository {

    val badges = listOf(
        Badge("first_steps", "First Steps", "Completed your very first cybersecurity lesson.", "Bolt", 30),
        Badge("phishing_spotter", "Phishing Spotter", "Mastered identifying fake links, emails, and scam messages.", "Security", 50),
        Badge("password_guardian", "Password Vault Guardian", "Learned to generate and manage unbreakable credentials.", "Lock", 50),
        Badge("privacy_sentinel", "Privacy Sentinel", "Configured smartphone permissions and data boundaries.", "Shield", 50),
        Badge("lab_master", "Lab Master", "Successfully completed 3 or more practical defensive labs.", "Science", 100),
        Badge("cyber_champion", "CyberSaathi Champion", "Completed 5 or more educational modules.", "EmojiEvents", 150)
    )

    val certificates = listOf(
        CourseCertificate("cert_fundamentals", "fundamentals_101", "Cybersecurity Fundamentals", "Fundamentals", "quiz_fundamentals_101"),
        CourseCertificate("cert_phishing", "phishing_101", "Phishing & Social Engineering Defense", "Phishing & Scams", "quiz_phishing_101"),
        CourseCertificate("cert_passwords", "passwords_101", "Password Security & Vault Management", "Passwords", "quiz_passwords_101"),
        CourseCertificate("cert_network", "network_101", "Network & Wi-Fi Security Defender", "Network Security", "quiz_network_101"),
        CourseCertificate("cert_web", "web_app_101", "Web & Application Security Essentials", "Web Security", "quiz_web_app_101"),
        CourseCertificate("cert_mobile", "mobile_101", "Mobile Security & Device Protection", "Mobile Security", "quiz_mobile_101"),
        CourseCertificate("cert_privacy", "privacy_101", "Digital Privacy & Identity Defense", "Privacy", "quiz_privacy_101"),
        CourseCertificate("cert_upi", "upi_101", "Digital Payments & UPI Fraud Prevention", "Payments", "quiz_upi_101")
    )

    val dailyTips = listOf(
        DailyTip(1, "Check the URL Before Tapping", "Scammers register web addresses that look almost identical to real banks or social networks.", "Look closely at domain spellings (e.g. paypaI.com with a capital 'I'). Type official URLs manually.", "Safe Browsing"),
        DailyTip(2, "Never Share UPI PIN to Receive Money", "UPI PIN is required ONLY to debit money from your account, NEVER to receive money.", "If someone asks for your UPI PIN or QR scan to receive money, it is a 100% SCAM.", "Payment Safety"),
        DailyTip(3, "Enable App Lock on Messaging & Banking", "If your phone is lost, app-level biometrics or PIN protects sensitive apps.", "Go to Settings > Security > App Lock and lock financial, email, and chat apps.", "Smartphone Security"),
        DailyTip(4, "Use Unique Passwords for Important Accounts", "If one website suffers a breach, hackers test that password on all your other accounts.", "Use a trusted Password Manager to store long, random passphrases for each site.", "Password Security"),
        DailyTip(5, "Be Wary of Urgent Action Demands", "Phishing messages create artificial panic: 'Account Suspended in 2 hours'.", "Pause and verify through official channels before clicking any link or sharing details.", "Social Engineering"),
        DailyTip(6, "Review App Permissions Regularly", "A simple flashlight or calculator app does not need access to Contacts, Camera, or SMS.", "Go to Settings > Privacy > Permission Manager and revoke unnecessary permissions.", "Privacy"),
        DailyTip(7, "Avoid Unencrypted Public Wi-Fi", "Open airport or café Wi-Fi networks can be monitored by attackers using MitM tools.", "Use mobile data or a trusted VPN when accessing sensitive bank accounts.", "Network Security")
    )

    val lessons = listOf(
        Lesson(
            id = "fundamentals_101",
            title = "Cybersecurity Fundamentals",
            subtitle = "Core security principles, CIA triad, attack vectors & defense in depth",
            category = "Fundamentals",
            durationMinutes = 6,
            difficulty = "Beginner",
            iconName = "Shield",
            sections = listOf(
                LessonSection(
                    title = "The CIA Triad",
                    bodyText = "The foundation of information security rests on three core principles:\n\n1. Confidentiality: Ensuring sensitive data is accessible ONLY to authorized users.\n2. Integrity: Guarding information against unauthorized modification or tampering.\n3. Availability: Ensuring systems and data are accessible to legitimate users whenever needed.",
                    warningTip = "A breach in any single pillar compromises the entire security boundary of an organization or user.",
                    practicalExample = "Encrypting a message protects Confidentiality. Digital signatures protect Integrity. Redundant server backups ensure Availability."
                ),
                LessonSection(
                    title = "Threats, Vulnerabilities & Risk",
                    bodyText = "Understanding risk requires distinguishing three key concepts:\n\n• Vulnerability: A flaw or weakness in software, hardware, or human procedures.\n• Threat: Any potential event or actor capable of exploiting a vulnerability.\n• Risk: The probability and financial/personal impact of a threat exploiting a vulnerability.\n\nRisk = Threat × Vulnerability × Impact.",
                    warningTip = "You cannot eliminate threats, but you can dramatically reduce risk by closing vulnerabilities and patching software.",
                    practicalExample = "An unpatched phone OS is a vulnerability. Malicious software on web downloads is a threat. Data theft is the risk."
                ),
                LessonSection(
                    title = "Defense in Depth & Least Privilege",
                    bodyText = "Defense in Depth is a security strategy employing multiple layers of defensive controls so that if one layer fails, subsequent layers block the attack.\n\nPrinciple of Least Privilege (PoLP): Users and software apps should be granted ONLY the minimum levels of access necessary to perform their legitimate tasks.",
                    warningTip = "Never run everyday computer activities logged in as System Administrator or Root.",
                    practicalExample = "A device using Screen Lock + App Encryption + 2FA + OS Sandbox implements Defense in Depth."
                )
            ),
            quizId = "quiz_fundamentals_101",
            labId = null
        ),

        Lesson(
            id = "phishing_101",
            title = "Phishing & Social Engineering",
            subtitle = "Spot fake emails, SMS, lookalike domains & scam triggers",
            category = "Phishing & Scams",
            durationMinutes = 5,
            difficulty = "Beginner",
            iconName = "Security",
            sections = listOf(
                LessonSection(
                    title = "What is Phishing?",
                    bodyText = "Phishing is a cyber attack where scammers impersonate trusted organizations (banks, delivery companies, government bodies) to trick you into revealing sensitive passwords, PINs, or credit card numbers.",
                    warningTip = "Phishers exploit psychological triggers like urgency, panic, fear, or financial greed.",
                    practicalExample = "SMS: 'Your bank account is frozen due to missing KYC. Click here immediately to update: http://bank-kyc-fix.info'."
                ),
                LessonSection(
                    title = "Key Red Flags of Phishing",
                    bodyText = "1. Sender Domain Inspection: Check the actual email domain address, not just the display name.\n2. Artificial Urgency: Threatening account suspension within 1-2 hours unless you act now.\n3. Lookalike URLs: Misspelled domains like 'amaz0n-security.com' or 'paypaI.com' (with a capital I).\n4. Unsolicited Attachments: ZIP files or executable files attached to unexpected invoices.",
                    warningTip = "Banks and legitimate services will NEVER demand your password or OTP over SMS or email.",
                    practicalExample = "An email claiming to be from Amazon arrives from 'support@amaz0n-security-update.xyz'."
                ),
                LessonSection(
                    title = "Defensive Protocol & Incident Response",
                    bodyText = "If you receive a suspicious message:\n• Do NOT click any links or download attachments.\n• Verify independently by visiting the official website directly.\n• If you mistakenly entered credentials on a phishing page, change your password IMMEDIATELY and enable 2FA.",
                    warningTip = "Never use contact phone numbers provided inside a suspicious email.",
                    practicalExample = "Search for your bank's official website manually in your browser instead of tapping text links."
                )
            ),
            quizId = "quiz_phishing_101",
            labId = "lab_phishing"
        ),

        Lesson(
            id = "passwords_101",
            title = "Password Security & Passphrases",
            subtitle = "Build unbreakable passphrases, use vaults & 2FA",
            category = "Password Security",
            durationMinutes = 5,
            difficulty = "Beginner",
            iconName = "Lock",
            sections = listOf(
                LessonSection(
                    title = "Why Short Passwords Fail",
                    bodyText = "Computers execute billions of password guesses per second using automated GPU cracking software. Short passwords like 'Password123' or 'Rahul@2024' are cracked in seconds.",
                    warningTip = "Length is the single most effective barrier against automated brute-force attacks. Aim for 14+ characters.",
                    practicalExample = "'P@ssword' takes 0.2 seconds to crack. 'Coffee#Blue#Tiger#99!' takes thousands of years."
                ),
                LessonSection(
                    title = "Passphrases & Password Managers",
                    bodyText = "Passphrases combine 4 or 5 random words with numbers and symbols (e.g. 'Mango#Galaxy#Thunder#88!'). They are easy for humans to remember but mathematically impossible for computers to guess.\n\nA Password Manager encrypts and stores unique passwords for every site in a digital vault.",
                    warningTip = "Never reuse the same password across multiple accounts (Credential Stuffing risk).",
                    practicalExample = "Bitwarden and 1Password generate and fill unique passwords for all your online accounts."
                ),
                LessonSection(
                    title = "Two-Factor Authentication (2FA)",
                    bodyText = "2FA requires two distinct authentication factors:\n1. Something you know (Password)\n2. Something you have (Authenticator App code or Hardware key).\n\nEven if a hacker steals your password, 2FA blocks unauthorized access.",
                    warningTip = "Use Authenticator Apps (Google Authenticator, Aegis) over SMS OTPs when possible.",
                    practicalExample = "Logging into Gmail requires your password PLUS the 6-digit code on your phone's Authenticator app."
                )
            ),
            quizId = "quiz_passwords_101",
            labId = "lab_password"
        ),

        Lesson(
            id = "network_101",
            title = "Network & Wi-Fi Security",
            subtitle = "Understand DNS, HTTPS, public Wi-Fi risks & VPNs",
            category = "Network Security",
            durationMinutes = 6,
            difficulty = "Intermediate",
            iconName = "Wifi",
            sections = listOf(
                LessonSection(
                    title = "How Network Traffic Travels",
                    bodyText = "When you browse the web, data travels across local routers, internet service providers (ISPs), and remote servers. Unencrypted traffic (HTTP) can be intercepted and read by anyone on the same local network (Sniffing / Man-in-the-Middle).",
                    warningTip = "HTTPS encrypts the data between your browser and the website server.",
                    practicalExample = "Always verify the 'https://' prefix when entering login credentials or financial card details."
                ),
                LessonSection(
                    title = "Public Wi-Fi Risks & VPNs",
                    bodyText = "Open public Wi-Fi networks (airports, cafes) can be easily impersonated by attackers setting up 'Rogue APs' or 'Evil Twin' routers.\n\nA Virtual Private Network (VPN) creates an encrypted tunnel for all your device traffic, protecting data on untrusted networks.",
                    warningTip = "Free VPNs often sell user browsing logs. Choose reputable, audited VPN providers.",
                    practicalExample = "Use mobile data or connect to a trusted VPN when conducting online banking in public locations."
                )
            ),
            quizId = "quiz_network_101",
            labId = "lab_url"
        ),

        Lesson(
            id = "web_app_101",
            title = "Web & Application Security",
            subtitle = "OWASP Top 10, SQL injection, XSS & secure coding",
            category = "Web Security",
            durationMinutes = 6,
            difficulty = "Intermediate",
            iconName = "Code",
            sections = listOf(
                LessonSection(
                    title = "Web Communication & Sessions",
                    bodyText = "Web applications communicate via HTTP Requests and Responses. Once logged in, the server issues a Session Cookie or Token to identify your authenticated state.",
                    warningTip = "Session cookies must be protected with 'HttpOnly' and 'Secure' flags to prevent theft.",
                    practicalExample = "Logging out clears your active session token on the server, preventing unauthorized access."
                ),
                LessonSection(
                    title = "Common Vulnerabilities (OWASP Top 10)",
                    bodyText = "• Injection (SQLi): Attackers insert malicious code into database query inputs.\n• Cross-Site Scripting (XSS): Malicious scripts injected into trusted web pages execute in users' browsers.\n• Broken Access Control: Failure to enforce permissions allows unauthorized data viewing.",
                    warningTip = "Input Validation and Parameterized Queries are essential defenses against web application attacks.",
                    practicalExample = "Using Prepared Statements prevents attackers from manipulating SQL queries via search forms."
                )
            ),
            quizId = "quiz_web_app_101",
            labId = null
        ),

        Lesson(
            id = "mobile_101",
            title = "Mobile Security & Protection",
            subtitle = "App permissions, APK risks, screen lock & SIM swap defense",
            category = "Mobile Security",
            durationMinutes = 5,
            difficulty = "Beginner",
            iconName = "Smartphone",
            sections = listOf(
                LessonSection(
                    title = "Android Permissions & Sideloading",
                    bodyText = "Apps request permission to access hardware and personal data (Camera, Microphone, Contacts, SMS, Storage). Sideloading modded APK files from third-party websites is a leading cause of Android malware infections.",
                    warningTip = "Revoke unnecessary permissions for single-purpose apps (e.g., flashlight apps asking for Contacts).",
                    practicalExample = "Download apps exclusively from trusted official app stores like Google Play Store."
                ),
                LessonSection(
                    title = "SIM Swap & Device Hardening",
                    bodyText = "SIM Swapping occurs when scammers trick phone carriers into transferring your phone number to their SIM card, hijacking SMS OTPs.\n\nSet a SIM PIN under phone settings to block unauthorized SIM transfer.",
                    warningTip = "Enable biometric / PIN screen lock and turn on device encryption.",
                    practicalExample = "Set a 6-digit SIM PIN under Settings > Security > SIM Lock to prevent SIM swapping."
                )
            ),
            quizId = "quiz_mobile_101",
            labId = "lab_permission"
        ),

        Lesson(
            id = "privacy_101",
            title = "Digital Privacy & Identity",
            subtitle = "Data minimization, location privacy & digital footprint",
            category = "Privacy",
            durationMinutes = 5,
            difficulty = "Beginner",
            iconName = "VisibilityOff",
            sections = listOf(
                LessonSection(
                    title = "Data Minimization & Tracking",
                    bodyText = "Data Minimization means sharing only the absolute minimum personal information required for a service to operate. Web trackers and advertising SDKs build extensive profiles based on online browsing history.",
                    warningTip = "Use privacy-focused browsers or ad blockers to limit third-party tracking scripts.",
                    practicalExample = "Grant 'Approximate Location' instead of 'Precise' for apps that do not require exact navigation."
                ),
                LessonSection(
                    title = "Social Media Privacy & Oversharing",
                    bodyText = "Posting real-time location tags, boarding passes, or identity documents exposes personal details used by attackers for identity theft or social engineering.",
                    warningTip = "Never publish photos showing QR codes from event tickets or boarding passes.",
                    practicalExample = "Set your social media profile privacy to 'Friends Only' to restrict public data harvesting."
                )
            ),
            quizId = "quiz_privacy_101",
            labId = "lab_permission"
        ),

        Lesson(
            id = "upi_101",
            title = "Digital Payments & UPI Fraud Safety",
            subtitle = "Protect money from QR scams, fake collect requests & 1930 helpline",
            category = "Payment Safety",
            durationMinutes = 5,
            difficulty = "Beginner",
            iconName = "AccountBalance",
            sections = listOf(
                LessonSection(
                    title = "Golden Rule of UPI",
                    bodyText = "UPI PIN is required ONLY to DEDUCT / SEND money from your bank account. You NEVER need to enter your UPI PIN or scan a QR code to RECEIVE money.",
                    warningTip = "If anyone tells you to enter your UPI PIN to receive payment or cashback, it is 100% a scam.",
                    practicalExample = "A buyer on OLX sends a QR code asking you to scan and enter PIN to accept money. This WILL deduct money from your account!"
                ),
                LessonSection(
                    title = "Common Payment Scams & Reporting",
                    bodyText = "1. Fake Collect Requests: Notifications asking you to approve money deduction.\n2. Screen-Sharing Abuse: Scammers asking you to install AnyDesk or TeamViewer to view your screen.\n3. National Cyber Crime Helpline: Call 1930 immediately in India if financial fraud occurs.",
                    warningTip = "Never install screen-sharing apps on instructions from unknown callers.",
                    practicalExample = "Decline collect requests from unknown VPAs and report financial cyber fraud to helpline 1930."
                )
            ),
            quizId = "quiz_upi_101",
            labId = "lab_upi"
        )
    )

    val quizzes = mapOf(
        "quiz_fundamentals_101" to Quiz(
            id = "quiz_fundamentals_101",
            lessonId = "fundamentals_101",
            title = "Cybersecurity Fundamentals Assessment",
            questions = listOf(
                QuizQuestion("q1", "What three core principles form the CIA Triad?", listOf("Code, Internet, Access", "Confidentiality, Integrity, Availability", "Cookies, IP, Accounts", "Cipher, Ingress, Authorization"), 1, "The CIA Triad stands for Confidentiality, Integrity, and Availability."),
                QuizQuestion("q2", "What is the formula for calculating Security Risk?", listOf("Risk = Speed × Memory", "Risk = Threat × Vulnerability × Impact", "Risk = Users ÷ Passwords", "Risk = Downloads + Uploads"), 1, "Risk is the product of Threat probability, Vulnerability severity, and Impact."),
                QuizQuestion("q3", "What does the Principle of Least Privilege (PoLP) dictate?", listOf("Give users maximum admin rights", "Grant users only the minimum access necessary for their tasks", "Allow public access to all databases", "Use short passwords"), 1, "PoLP ensures users and applications have only the minimal access needed to perform their job."),
                QuizQuestion("q4", "What is Defense in Depth?", listOf("Using only one strong password", "Using multiple layers of security controls", "Storing backups in the cloud only", "Turning off Wi-Fi"), 1, "Defense in depth uses overlapping security layers so that if one fails, others block the attack."),
                QuizQuestion("q5", "Which of the following represents a Vulnerability?", listOf("An unpatched software security flaw", "A hacker sitting in a cafe", "A data breach incident", "An antivirus scanner"), 0, "A vulnerability is a weakness or flaw in software, hardware, or procedures.")
            )
        ),

        "quiz_phishing_101" to Quiz(
            id = "quiz_phishing_101",
            lessonId = "phishing_101",
            title = "Phishing & Social Engineering Check",
            questions = listOf(
                QuizQuestion("q1", "What is the primary objective of a phishing attack?", listOf("To optimize your network speed", "To trick you into surrendering sensitive credentials or info", "To clean browser cookies", "To update device firmware"), 1, "Phishing tricks victims into handing over passwords, PINs, or financial data."),
                QuizQuestion("q2", "You get an SMS: 'Your bank account will be blocked! Click bit.ly/3x8K to fix.' Safest action?", listOf("Click the link immediately", "Reply with your account number", "Ignore the link & verify via official bank app", "Forward to all contacts"), 2, "Never tap suspicious short links in text messages. Verify directly via official bank channels."),
                QuizQuestion("q3", "Which sender address is a fake phishing email impersonating Netflix?", listOf("support@netflix.com", "billing@netflix-account-update.xyz", "info@netflix.com", "no-reply@netflix.com"), 1, "Scammers use lookalike domains like 'netflix-account-update.xyz' to mimic real brands."),
                QuizQuestion("q4", "What psychological tactic do phishing messages frequently exploit?", listOf("Relaxed deadlines", "Urgency and fear of immediate penalty", "Scientific explanations", "Boring technical jargon"), 1, "Phishers use artificial panic ('Account blocked in 1 hour!') to make victims act without thinking."),
                QuizQuestion("q5", "What should you do immediately if you entered your password on a fake page?", listOf("Wait 24 hours", "Change your password & turn on 2FA on the official account", "Delete browser", "Turn off Bluetooth"), 1, "Change compromised passwords immediately and turn on 2FA to block unauthorized logins.")
            )
        ),

        "quiz_passwords_101" to Quiz(
            id = "quiz_passwords_101",
            lessonId = "passwords_101",
            title = "Password Security Assessment",
            questions = listOf(
                QuizQuestion("q1", "Which password provides the strongest defense against cracking software?", listOf("Password123", "Rahul@2024", "Coffee#Blue#Tiger#99!", "12345678"), 2, "Passphrases combining 4+ random words with numbers/symbols provide 20+ character length."),
                QuizQuestion("q2", "Why is password reuse dangerous?", listOf("It slows Wi-Fi", "If one site is breached, attackers try that password everywhere", "It uses phone storage", "It causes typos"), 1, "Credential stuffing attacks test leaked password lists across hundreds of popular services."),
                QuizQuestion("q3", "What is the main benefit of a Password Manager?", listOf("It posts on social media", "It creates and remembers unique, strong passwords for every site", "It speeds up charging", "It deletes emails"), 1, "Password managers encrypt credentials locally so you only remember one master password."),
                QuizQuestion("q4", "Recommended minimum character length for strong passwords?", listOf("4 characters", "6 characters", "14+ characters", "8 characters max"), 2, "Modern standards recommend 14+ characters to resist GPU cracking tools."),
                QuizQuestion("q5", "Where is it UNSAFE to store account passwords?", listOf("Encrypted Password Manager", "Plain text file on desktop or phone notes", "Bitwarden Vault", "1Password Vault"), 1, "Unencrypted plain text files can be read by malicious apps or thieves.")
            )
        ),

        "quiz_network_101" to Quiz(
            id = "quiz_network_101",
            lessonId = "network_101",
            title = "Network Security Check",
            questions = listOf(
                QuizQuestion("q1", "What does HTTPS do for your web traffic?", listOf("Makes websites load 10x faster", "Encrypts data between browser and website server", "Deletes web ads", "Blocks phone calls"), 1, "HTTPS encrypts communications between your browser and the website."),
                QuizQuestion("q2", "Why is unencrypted public Wi-Fi risky?", listOf("It drains battery", "Attackers on the network can sniff unencrypted traffic", "It turns off screen lock", "It deletes app files"), 1, "Public Wi-Fi allows attackers to intercept unencrypted HTTP traffic."),
                QuizQuestion("q3", "What is a major benefit of a VPN on untrusted networks?", listOf("Free internet access", "Creates an encrypted tunnel for all network traffic", "Increases screen resolution", "Deletes spam SMS"), 1, "VPNs encrypt data traffic between your device and the VPN server."),
                QuizQuestion("q4", "What is an 'Evil Twin' Wi-Fi attack?", listOf("A virus that doubles files", "A rogue fake Wi-Fi access point set up by attackers to mimic real Wi-Fi", "A double router", "A satellite signal"), 1, "Evil Twin access points trick devices into connecting to malicious hacker routers."),
                QuizQuestion("q5", "Which prefix in the browser address bar confirms transport encryption?", listOf("http://", "https://", "ftp://", "web://"), 1, "'https://' indicates encrypted HTTP Secure connection.")
            )
        ),

        "quiz_web_app_101" to Quiz(
            id = "quiz_web_app_101",
            lessonId = "web_app_101",
            title = "Web Security Assessment",
            questions = listOf(
                QuizQuestion("q1", "What is SQL Injection (SQLi)?", listOf("Injecting hardware memory", "Inserting malicious SQL code into input fields to query databases unauthorized", "Updating web browser", "Formatting hard drives"), 1, "SQLi manipulates backend database queries via unvalidated user inputs."),
                QuizQuestion("q2", "What is Cross-Site Scripting (XSS)?", listOf("Connecting two monitors", "Injecting malicious scripts into web pages viewed by other users", "Downloading APKs", "Using dark mode"), 1, "XSS executes unauthorized scripts in the victim's browser context."),
                QuizQuestion("q3", "What primary defensive coding practice prevents SQL Injection?", listOf("Using short passwords", "Parameterized Queries / Prepared Statements", "Turning off server firewalls", "Increasing screen brightness"), 1, "Prepared statements separate SQL code from user-supplied data input."),
                QuizQuestion("q4", "What does a Session Cookie do?", listOf("Stores browser theme", "Identifies an authenticated user session on the web server", "Speeds up CPU", "Saves photos"), 1, "Session cookies maintain your logged-in state across web page requests."),
                QuizQuestion("q5", "What organization publishes the famous 'Top 10' web security risks list?", listOf("OWASP", "IEEE", "W3C", "ISO"), 0, "OWASP (Open Worldwide Application Security Project) publishes the OWASP Top 10.")
            )
        ),

        "quiz_mobile_101" to Quiz(
            id = "quiz_mobile_101",
            lessonId = "mobile_101",
            title = "Mobile Security Assessment",
            questions = listOf(
                QuizQuestion("q1", "What is the main risk of sideloading modded APK files from random websites?", listOf("App icons change color", "APKs can contain hidden malware, keyloggers, or spyware", "Battery charges faster", "Storage doubles"), 1, "Third-party APK files can be injected with trojans or spyware."),
                QuizQuestion("q2", "Is it safe for a simple calculator app to request SMS and Camera access?", listOf("Yes, all apps need permissions", "No, it is a major privacy risk and unnecessary for a calculator", "Yes, on weekends", "Only if free"), 1, "Single-purpose apps requesting excessive permissions pose security risks."),
                QuizQuestion("q3", "What is SIM Swapping?", listOf("Changing phone cases", "Tricking mobile carriers into transferring your phone number to a hacker's SIM", "Using dual SIMs", "Upgrading to 5G"), 1, "SIM swapping hijacks your phone number to intercept SMS OTPs."),
                QuizQuestion("q4", "How can you defend your phone number against SIM swapping?", listOf("Set a SIM PIN under phone settings", "Delete all contacts", "Turn off Bluetooth", "Use airplane mode"), 0, "A SIM PIN blocks unauthorized transfer of your mobile line."),
                QuizQuestion("q5", "Where should you manage and revoke app permissions on Android?", listOf("Settings > Privacy / Permission Manager", "Calculator App", "Phone Dialer", "Clock App"), 0, "Android Permission Manager under Settings lets you view and revoke app privileges.")
            )
        ),

        "quiz_privacy_101" to Quiz(
            id = "quiz_privacy_101",
            lessonId = "privacy_101",
            title = "Digital Privacy Assessment",
            questions = listOf(
                QuizQuestion("q1", "What is Data Minimization?", listOf("Deleting all photos", "Providing only the minimum personal data necessary for a service to function", "Using smaller screens", "Compressing files"), 1, "Data minimization reduces personal data exposure during breaches."),
                QuizQuestion("q2", "Why is posting photos of your flight boarding pass online dangerous?", listOf("Boarding pass barcodes contain personal passenger data and PNR numbers", "Photos expire after 1 hour", "Friends will dislike the photo", "It wastes battery"), 0, "Barcodes on boarding passes reveal passenger name, booking reference (PNR), and flight details."),
                QuizQuestion("q3", "What location setting should you select for non-navigation apps?", listOf("Always Allow Precise Location", "Approximate Location / Only While Using App", "Allow All Time in Background", "Never update app"), 1, "Limiting apps to approximate location reduces tracking while preserving functionality."),
                QuizQuestion("q4", "How can you restrict who sees your social media posts?", listOf("Set Profile Privacy to Private / Friends Only", "Turn on Airplane Mode", "Use dark mode", "Change wallpaper"), 0, "Restricting profile visibility prevents public data harvesting by scammers."),
                QuizQuestion("q5", "What should you do if an imposter profile imitates you on social media?", listOf("Report the fake profile to platform support and warn your contacts", "Do nothing", "Pay money", "Delete your phone"), 0, "Reporting imposter accounts gets them removed and protects your contacts from fraud.")
            )
        ),

        "quiz_upi_101" to Quiz(
            id = "quiz_upi_101",
            lessonId = "upi_101",
            title = "UPI & Payment Fraud Assessment",
            questions = listOf(
                QuizQuestion("q1", "When is your UPI PIN required during a transaction?", listOf("To receive money from a friend", "ONLY to debit/send money from your account", "To claim cashbacks", "To check phone battery"), 1, "Golden Rule: UPI PIN is required ONLY when money leaves your bank account!"),
                QuizQuestion("q2", "Someone on OLX sends a QR code claiming 'Scan QR to receive ₹2,000'. What happens if you scan and enter PIN?", listOf("You receive ₹2,000", "₹2,000 is deducted from YOUR account!", "Nothing happens", "Phone restarts"), 1, "Scanning QR codes and entering PINs is a payment request that deducts money from your account."),
                QuizQuestion("q3", "A caller claims to be customer care and asks you to install 'AnyDesk' or 'TeamViewer'. Why?", listOf("To fix network speed", "To remotely view your screen and steal payment PINs/OTPs", "To gift free money", "To update Android"), 1, "Screen-sharing apps allow scammers to view your banking screens and OTPs in real-time."),
                QuizQuestion("q4", "What should you do if you receive a 'Collect Request' from an unknown UPI ID?", listOf("Approve it immediately", "Decline / Ignore the request", "Enter wrong PIN", "Forward to contacts"), 1, "Decline collect requests from unknown callers or VPAs immediately."),
                QuizQuestion("q5", "What official helpline number should you call in India after financial cyber fraud?", listOf("1930 (National Cyber Crime Helpline)", "100", "101", "102"), 0, "1930 is the official National Cyber Crime Helpline in India for reporting financial fraud swiftly.")
            )
        )
    )

    val labs = listOf(
        Lab("lab_phishing", "phishing_101", "Phishing Email Inspector Lab", "Phishing & Scams", "Inspect a suspicious email message, identify key red flags, and make the defensive call.", LabType.PHISHING_INSPECTOR, 75),
        Lab("lab_url", "network_101", "URL & Link Trust Analyzer Lab", "Safe Browsing", "Analyze incoming web links, spot typosquatting, and determine if the domain is safe.", LabType.URL_ANALYZER, 75),
        Lab("lab_password", "passwords_101", "Password Strength Inspector Lab", "Password Security", "Test interactive password inputs against local entropy, common patterns, and brute-force estimate models.", LabType.PASSWORD_STRENGTH, 75),
        Lab("lab_permission", "privacy_101", "App Permission Auditor Lab", "Privacy", "Audit requested permissions for a fictional app and revoke risky, unnecessary privileges.", LabType.PERMISSION_AUDITOR, 75),
        Lab("lab_upi", "upi_101", "UPI Payment Scam Simulator", "Payment Safety", "Simulate an incoming digital payment scenario and select the safe defensive action.", LabType.UPI_SCAM_DEFENDER, 75)
    )
}
