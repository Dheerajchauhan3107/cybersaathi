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
        CourseCertificate("cert_fundamentals", "fundamentals_101", "Cybersecurity Fundamentals", "Fundamentals", "quiz_fundamentals_101", null),
        CourseCertificate("cert_phishing", "phishing_101", "Phishing & Social Engineering Defense", "Phishing & Scams", "quiz_phishing_101", "lab_phishing"),
        CourseCertificate("cert_passwords", "passwords_101", "Password Security & Vault Management", "Passwords", "quiz_passwords_101", "lab_password"),
        CourseCertificate("cert_network", "network_101", "Network & Wi-Fi Security Defender", "Network Security", "quiz_network_101", "lab_url"),
        CourseCertificate("cert_web", "web_app_101", "Web & Application Security Essentials", "Web Security", "quiz_web_app_101", null),
        CourseCertificate("cert_mobile", "mobile_101", "Mobile Security & Device Protection", "Mobile Security", "quiz_mobile_101", "lab_permission"),
        CourseCertificate("cert_privacy", "privacy_101", "Digital Privacy & Identity Defense", "Privacy", "quiz_privacy_101", null),
        CourseCertificate("cert_upi", "upi_101", "Digital Payments & UPI Fraud Prevention", "Payments", "quiz_upi_101", "lab_upi")
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
            subtitle = "CIA triad, threats, vulnerabilities, risk, security controls & defense in depth",
            category = "Fundamentals",
            durationMinutes = 8,
            difficulty = "Beginner",
            iconName = "Shield",
            sections = listOf(
                LessonSection(
                    title = "The CIA Triad Principles",
                    bodyText = "The foundational pillar of information security rests on three critical criteria:\n\n1. Confidentiality: Ensuring sensitive assets are accessible ONLY to authorized entities.\n2. Integrity: Guarding information against unauthorized creation, tampering, or deletion.\n3. Availability: Guaranteeing that systems and data remain operational and reachable by authorized users when needed.",
                    warningTip = "A failure in any single component compromises the entire security posture.",
                    practicalExample = "Encrypting database records maintains Confidentiality. Using cryptographic hashing ensures Integrity. Multi-region server backups ensure Availability."
                ),
                LessonSection(
                    title = "Threats, Vulnerabilities & Risk",
                    bodyText = "Understanding risk requires precise definitions:\n\n• Vulnerability: A weakness in system code, hardware, or human operational procedures.\n• Threat: Any circumstance or actor with the potential to exploit a vulnerability.\n• Risk: The likelihood of a threat manifesting times the resulting impact.\n\nRisk = Threat Probability × Vulnerability Severity × Impact.",
                    warningTip = "You cannot stop all threats, but you can systematically reduce risk by closing vulnerabilities and applying timely patches.",
                    practicalExample = "An unpatched phone OS is a vulnerability. Active malware on unverified downloads is a threat. Private data theft is the impact."
                ),
                LessonSection(
                    title = "Defense in Depth & Least Privilege",
                    bodyText = "Defense in Depth is a security architecture combining multiple layers of defensive controls. If one layer fails, subsequent layers block the attack.\n\nPrinciple of Least Privilege (PoLP): Users and software apps must be granted ONLY the minimum necessary access privileges required to complete their authorized tasks.",
                    warningTip = "Never run everyday software or browser sessions logged in with root or administrator privileges.",
                    practicalExample = "Device Screen Lock + App Encryption + 2FA + OS Sandboxing forms robust Defense in Depth."
                )
            ),
            quizId = "quiz_fundamentals_101",
            labId = null
        ),

        Lesson(
            id = "phishing_101",
            title = "Phishing & Social Engineering",
            subtitle = "Email, SMS, messaging scams, lookalike domains & URL inspection",
            category = "Phishing & Scams",
            durationMinutes = 7,
            difficulty = "Beginner",
            iconName = "Security",
            sections = listOf(
                LessonSection(
                    title = "Anatomy of a Phishing Attack",
                    bodyText = "Phishing is social engineering delivered via digital channels. Attackers impersonate trusted authorities (banks, tax agencies, courier services) to manipulate victims into disclosing credentials, financial accounts, or OTPs.",
                    warningTip = "Scammers exploit emotional triggers like panic, curiosity, or financial reward to bypass rational thinking.",
                    practicalExample = "SMS: 'Your bank account is frozen due to missing KYC. Click here immediately to update: http://bank-kyc-fix.info'."
                ),
                LessonSection(
                    title = "Key Red Flags & Indicator Analysis",
                    bodyText = "1. Sender Domain Verification: Inspect the actual email domain address, not just the display name.\n2. Artificial Urgency: Threatening account suspension within 1-2 hours to rush decisions.\n3. Lookalike URLs: Misspelled domains like 'amaz0n-security.com' or 'paypaI.com' (using capital 'I').\n4. Unsolicited Attachments: ZIP archives or executable files attached to unexpected invoices.",
                    warningTip = "Legitimate institutions will never demand passwords or OTPs over SMS or email.",
                    practicalExample = "An email claiming to be from Netflix arriving from 'billing@netflix-account-update.xyz'."
                ),
                LessonSection(
                    title = "Defensive Incident Response",
                    bodyText = "If you encounter a suspicious message:\n• Do NOT click links or download attachments.\n• Verify independently via official apps or bookmarked websites.\n• If you entered credentials on a phishing page, change your password immediately and verify 2FA settings.",
                    warningTip = "Never dial phone numbers listed inside suspicious phishing emails.",
                    practicalExample = "Type your bank's official web address directly into the browser instead of tapping text links."
                )
            ),
            quizId = "quiz_phishing_101",
            labId = "lab_phishing"
        ),

        Lesson(
            id = "passwords_101",
            title = "Password Security & Passphrases",
            subtitle = "Entropy, passphrase generation, password managers & 2FA",
            category = "Password Security",
            durationMinutes = 7,
            difficulty = "Beginner",
            iconName = "Lock",
            sections = listOf(
                LessonSection(
                    title = "Entropy and Brute-Force Defense",
                    bodyText = "Computers test billions of password guesses per second using automated GPU cracking algorithms. Short passwords like 'Password123' or 'Rahul@2024' are cracked instantly.\n\nLength and randomness (entropy) are the most powerful defenses. Aim for 14+ characters.",
                    warningTip = "Never reuse passwords across different online services (Credential Stuffing risk).",
                    practicalExample = "'P@ssword' takes 0.2 seconds to crack. 'Coffee#Blue#Tiger#99!' takes thousands of years."
                ),
                LessonSection(
                    title = "Passphrases & Password Managers",
                    bodyText = "Passphrases combine 4 or 5 random words with numbers and symbols (e.g. 'Mango#Galaxy#Thunder#88!'). They are easy for humans to remember and mathematically secure against brute force.\n\nA Password Manager encrypts and stores unique credentials for every site in a digital vault.",
                    warningTip = "Master Password for your vault must be exceptionally strong and memorized securely.",
                    practicalExample = "Bitwarden and 1Password generate and autofill unique credentials for all accounts."
                ),
                LessonSection(
                    title = "Two-Factor Authentication (2FA)",
                    bodyText = "2FA requires two distinct authentication factors:\n1. Something you know (Password)\n2. Something you have (Authenticator App code).\n\nEven if an attacker steals your password, 2FA blocks unauthorized access.",
                    warningTip = "Prefer Authenticator Apps (Google Authenticator, Aegis) over vulnerable SMS OTPs.",
                    practicalExample = "Logging into Gmail requires your password PLUS the 6-digit TOTP code on your phone."
                )
            ),
            quizId = "quiz_passwords_101",
            labId = "lab_password"
        ),

        Lesson(
            id = "network_101",
            title = "Network & Wi-Fi Security",
            subtitle = "DNS, TCP/IP, HTTPS/TLS, public Wi-Fi risks & VPNs",
            category = "Network Security",
            durationMinutes = 7,
            difficulty = "Intermediate",
            iconName = "Wifi",
            sections = listOf(
                LessonSection(
                    title = "HTTP vs HTTPS and TLS Encryption",
                    bodyText = "When browsing the web, unencrypted HTTP traffic can be intercepted and inspected by anyone on the same local network (Sniffing / Man-in-the-Middle).\n\nHTTPS uses TLS (Transport Layer Security) to encrypt all communication between your browser and the server.",
                    warningTip = "Always ensure the 'https://' prefix is present when transmitting sensitive credentials.",
                    practicalExample = "Banking and e-commerce platforms mandate HTTPS to secure transactions in transit."
                ),
                LessonSection(
                    title = "Public Wi-Fi Risks & VPNs",
                    bodyText = "Open public Wi-Fi networks (airports, cafes) can be impersonated by attackers using 'Evil Twin' rogue routers.\n\nA Virtual Private Network (VPN) creates an encrypted tunnel for all device traffic, shielding data on untrusted networks.",
                    warningTip = "Free VPNs often monetize by logging and selling user browsing data. Use reputable audited VPN providers.",
                    practicalExample = "Use mobile data or a trusted VPN when accessing sensitive accounts over public Wi-Fi."
                )
            ),
            quizId = "quiz_network_101",
            labId = "lab_url"
        ),

        Lesson(
            id = "web_app_101",
            title = "Web & Application Security",
            subtitle = "HTTP requests, sessions, cookies, SQL injection & XSS",
            category = "Web Security",
            durationMinutes = 8,
            difficulty = "Intermediate",
            iconName = "Code",
            sections = listOf(
                LessonSection(
                    title = "Sessions, Cookies & Access Control",
                    bodyText = "Web apps communicate via HTTP Requests and Responses. Once authenticated, the server issues a Session Cookie or Token to identify your logged-in state.\n\nAccess Control ensures users can access only data authorized for their role.",
                    warningTip = "Session cookies must be protected with 'HttpOnly' and 'Secure' flags to prevent theft.",
                    practicalExample = "Logging out invalidates your active session token on the server."
                ),
                LessonSection(
                    title = "SQL Injection & Cross-Site Scripting (XSS)",
                    bodyText = "• SQL Injection (SQLi): Attackers insert malicious SQL code into database query inputs to extract or manipulate database records.\n• Cross-Site Scripting (XSS): Malicious scripts injected into trusted web pages execute inside victim browsers.",
                    warningTip = "Parameterized Queries (Prepared Statements) and Output Encoding are essential web security defenses.",
                    practicalExample = "Prepared statements separate SQL commands from user-supplied search parameters."
                )
            ),
            quizId = "quiz_web_app_101",
            labId = null
        ),

        Lesson(
            id = "mobile_101",
            title = "Mobile Security & Device Protection",
            subtitle = "App permissions, APK sideloading, screen locks & SIM swap defense",
            category = "Mobile Security",
            durationMinutes = 6,
            difficulty = "Beginner",
            iconName = "Smartphone",
            sections = listOf(
                LessonSection(
                    title = "App Permissions & Sideloading Risks",
                    bodyText = "Mobile apps request permissions to access hardware (Camera, Microphone, Location, Contacts, SMS). Sideloading modded APK files from unverified websites is a primary cause of Android malware infections.",
                    warningTip = "Revoke unnecessary permissions for single-purpose apps in Android Permission Manager.",
                    practicalExample = "Always install mobile apps exclusively from official trusted stores like Google Play Store."
                ),
                LessonSection(
                    title = "SIM Swap & Device Hardening",
                    bodyText = "SIM Swapping occurs when scammers trick mobile carriers into transferring your phone number to their SIM card to hijack SMS OTPs.\n\nSet a SIM PIN under phone settings to block unauthorized SIM transfer.",
                    warningTip = "Enable biometric / PIN screen lock and turn on device encryption.",
                    practicalExample = "Set a 6-digit SIM PIN under Settings > Security > SIM Lock to prevent SIM hijacking."
                )
            ),
            quizId = "quiz_mobile_101",
            labId = "lab_permission"
        ),

        Lesson(
            id = "privacy_101",
            title = "Digital Privacy & Identity",
            subtitle = "Data minimization, tracking, location privacy & social media hygiene",
            category = "Privacy",
            durationMinutes = 6,
            difficulty = "Beginner",
            iconName = "VisibilityOff",
            sections = listOf(
                LessonSection(
                    title = "Data Minimization & Digital Footprint",
                    bodyText = "Data Minimization means sharing only the absolute minimum personal data required for a service to operate. Web trackers and advertising SDKs build extensive behavioural profiles based on browsing history.",
                    warningTip = "Use privacy-focused browsers and limit app tracking permissions.",
                    practicalExample = "Grant 'Approximate Location' instead of 'Precise' for apps that do not require exact GPS mapping."
                ),
                LessonSection(
                    title = "Social Media Privacy & Oversharing",
                    bodyText = "Posting real-time location tags, boarding passes, or identity documents exposes personal details used by attackers for identity theft or social engineering.",
                    warningTip = "Never publish photos showing QR codes from event tickets or boarding passes.",
                    practicalExample = "Set social media profile privacy to 'Friends Only' to restrict public data harvesting."
                )
            ),
            quizId = "quiz_privacy_101",
            labId = null
        ),

        Lesson(
            id = "upi_101",
            title = "Digital Payments & UPI Fraud Safety",
            subtitle = "UPI PIN rules, QR scams, screen-sharing apps & 1930 cyber helpline",
            category = "Payment Safety",
            durationMinutes = 6,
            difficulty = "Beginner",
            iconName = "AccountBalance",
            sections = listOf(
                LessonSection(
                    title = "Golden Rule of UPI Payments",
                    bodyText = "UPI PIN is required ONLY to DEDUCT / SEND money from your bank account. You NEVER need to enter your UPI PIN or scan a QR code to RECEIVE money.",
                    warningTip = "If anyone tells you to enter your UPI PIN to receive a payment or refund, it is 100% a scam.",
                    practicalExample = "A buyer on OLX sends a QR code asking you to scan and enter PIN to accept money. This WILL deduct money from your account!"
                ),
                LessonSection(
                    title = "Payment Scams & Reporting in India",
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
            title = "Cybersecurity Fundamentals Comprehensive Exam",
            passingScorePercentage = 70,
            xpReward = 50,
            questions = listOf(
                QuizQuestion("q1", "What three core principles form the foundational CIA Triad in cybersecurity?", listOf("Code, Internet, Access", "Confidentiality, Integrity, Availability", "Cookies, IP, Accounts", "Cipher, Ingress, Authorization"), 1, "The CIA Triad stands for Confidentiality, Integrity, and Availability."),
                QuizQuestion("q2", "How is security risk mathematically or conceptually modeled?", listOf("Risk = Speed × Memory", "Risk = Threat Probability × Vulnerability Severity × Impact", "Risk = Users ÷ Passwords", "Risk = Downloads + Uploads"), 1, "Risk is the product of Threat probability, Vulnerability severity, and Impact."),
                QuizQuestion("q3", "What does the Principle of Least Privilege (PoLP) dictate?", listOf("Give users maximum administrator rights", "Grant users and applications only the minimum access necessary for their tasks", "Allow public access to all databases", "Use short passwords"), 1, "PoLP ensures users and apps have only minimal access needed to perform their jobs."),
                QuizQuestion("q4", "What is Defense in Depth?", listOf("Using only one strong password", "Using multiple overlapping layers of security controls", "Storing backups in the cloud only", "Turning off Wi-Fi"), 1, "Defense in depth uses overlapping security layers so that if one fails, others block the attack."),
                QuizQuestion("q5", "Which of the following represents a system Vulnerability?", listOf("An unpatched software security flaw", "A hacker sitting in a café", "A data breach incident", "An antivirus scanner"), 0, "A vulnerability is a weakness or flaw in software, hardware, or operational procedures."),
                QuizQuestion("q6", "What is the primary purpose of data encryption?", listOf("To compress file sizes", "To ensure Confidentiality by rendering data unreadable without the key", "To speed up server responses", "To delete malware"), 1, "Encryption protects Confidentiality against unauthorized interception."),
                QuizQuestion("q7", "Which control protects data Integrity?", listOf("Multi-region server power", "Cryptographic digital signatures and checksums", "Public IP addresses", "Firewall packet filtering"), 1, "Cryptographic signatures and hashes verify that data has not been tampered with."),
                QuizQuestion("q8", "What ensures Availability in the CIA triad?", listOf("Redundant backups, fault-tolerant servers, and DDoS protection", "Strong master passwords", "Secret encryption keys", "Closing firewall ports"), 0, "Availability ensures systems and data remain operational and accessible when needed."),
                QuizQuestion("q9", "What is an attack surface?", listOf("The physical weight of a server", "The sum of all vulnerable points where an unauthorized attacker can try to enter data", "The speed of a network router", "The screen brightness of a phone"), 1, "The attack surface represents all entry points susceptible to exploitation."),
                QuizQuestion("q10", "Why is security awareness training important for human users?", listOf("Humans are often targeted as the weakest link via social engineering", "It makes computers run faster", "It replaces antivirus software", "It generates network bandwidth"), 0, "Security awareness mitigates human social engineering risks.")
            )
        ),

        "quiz_phishing_101" to Quiz(
            id = "quiz_phishing_101",
            lessonId = "phishing_101",
            title = "Phishing & Social Engineering Assessment",
            passingScorePercentage = 70,
            xpReward = 50,
            questions = listOf(
                QuizQuestion("q1", "What is the primary objective of a phishing attack?", listOf("To optimize network speed", "To trick victims into surrendering sensitive credentials or info", "To clean browser cookies", "To update firmware"), 1, "Phishing tricks victims into handing over passwords, PINs, or financial data."),
                QuizQuestion("q2", "You get an SMS: 'Your bank account will be blocked! Click bit.ly/3x8K to fix.' Safest action?", listOf("Click the link immediately", "Reply with your account number", "Ignore the link & verify via official bank app", "Forward to all contacts"), 2, "Never tap suspicious short links in text messages. Verify directly via official bank channels."),
                QuizQuestion("q3", "Which sender address is a fake phishing email impersonating Netflix?", listOf("support@netflix.com", "billing@netflix-account-update.xyz", "info@netflix.com", "no-reply@netflix.com"), 1, "Scammers use lookalike domains like 'netflix-account-update.xyz' to mimic real brands."),
                QuizQuestion("q4", "What psychological tactic do phishing messages frequently exploit?", listOf("Relaxed deadlines", "Urgency and fear of immediate penalty", "Scientific explanations", "Boring technical jargon"), 1, "Phishers use artificial panic ('Account blocked in 1 hour!') to make victims act without thinking."),
                QuizQuestion("q5", "What should you do immediately if you entered your password on a fake page?", listOf("Wait 24 hours", "Change your password & turn on 2FA on the official account", "Delete browser", "Turn off Bluetooth"), 1, "Change compromised passwords immediately and turn on 2FA to block unauthorized logins."),
                QuizQuestion("q6", "What is Business Email Compromise (BEC)?", listOf("A corrupted hard drive", "Targeted spear-phishing impersonating company executives to wire funds", "Spam emails about discounts", "Slow internet connection"), 1, "BEC targets corporate finance communications to redirect funds."),
                QuizQuestion("q7", "How can you inspect a hyperlink destination before clicking on mobile?", listOf("Tap it quickly", "Long-press the link to preview the target URL address", "Shake the phone", "Turn off Wi-Fi"), 1, "Long-pressing links on mobile reveals the underlying URL destination."),
                QuizQuestion("q8", "What is Quishing (QR Phishing)?", listOf("Quick Wi-Fi setup", "Scam QR codes placed in public places or phishing emails leading to fake login portals", "Quiet chat messaging", "Quick system reboot"), 1, "Quishing uses malicious QR codes to bypass traditional email filters."),
                QuizQuestion("q9", "Why do phishers use lookalike (typosquatted) domains?", listOf("To save server costs", "To trick users into believing they are visiting an official trusted website", "To encrypt traffic", "To speed up loading"), 1, "Typosquatting exploits subtle spelling errors to deceive victims."),
                QuizQuestion("q10", "What is the safest way to respond to an unexpected security alert email from your bank?", listOf("Click the email link and login", "Call the phone number listed inside the email", "Open your official banking app or typed web address independently", "Reply with your password"), 2, "Always verify alerts independently via official trusted apps or bookmarks.")
            )
        ),

        "quiz_passwords_101" to Quiz(
            id = "quiz_passwords_101",
            lessonId = "passwords_101",
            title = "Password Security Assessment",
            passingScorePercentage = 70,
            xpReward = 50,
            questions = listOf(
                QuizQuestion("q1", "Which password provides the strongest defense against cracking software?", listOf("Password123", "Rahul@2024", "Coffee#Blue#Tiger#99!", "12345678"), 2, "Passphrases combining 4+ random words with numbers/symbols provide 20+ character length."),
                QuizQuestion("q2", "Why is password reuse dangerous?", listOf("It slows Wi-Fi", "If one site is breached, attackers try that password everywhere", "It uses phone storage", "It causes typos"), 1, "Credential stuffing attacks test leaked password lists across hundreds of popular services."),
                QuizQuestion("q3", "What is the main benefit of a Password Manager?", listOf("It posts on social media", "It creates and remembers unique, strong passwords for every site", "It speeds up charging", "It deletes emails"), 1, "Password managers encrypt credentials locally so you only remember one master password."),
                QuizQuestion("q4", "Recommended minimum character length for strong passwords?", listOf("4 characters", "6 characters", "14+ characters", "8 characters max"), 2, "Modern standards recommend 14+ characters to resist GPU cracking tools."),
                QuizQuestion("q5", "Where is it UNSAFE to store account passwords?", listOf("Encrypted Password Manager", "Plain text file on desktop or phone notes", "Bitwarden Vault", "1Password Vault"), 1, "Unencrypted plain text files can be read by malicious apps or thieves."),
                QuizQuestion("q6", "What is Credential Stuffing?", listOf("Eating snacks while typing passwords", "Automated injection of stolen username/password pairs across multiple websites", "Backing up password files", "Resetting PINs"), 1, "Credential stuffing exploits password reuse across websites."),
                QuizQuestion("q7", "What is a Master Password in a password manager?", listOf("Your ATM PIN", "The single strong password required to unlock your encrypted password vault", "Your phone lock code", "Your email address"), 1, "The master password protects the entire encrypted password database."),
                QuizQuestion("q8", "Why are SMS-based OTPs less secure than Authenticator apps?", listOf("SMS codes never expire", "SMS is vulnerable to SIM-swapping and cellular interception", "SMS is too fast", "Authenticator apps require internet"), 1, "SMS messages travel over vulnerable cellular signaling networks."),
                QuizQuestion("q9", "What is a Passkey?", listOf("A metal key for servers", "A cryptographic FIDO2 credential replacing passwords with biometric verification", "A password written on paper", "An email token"), 1, "Passkeys provide phishing-resistant authentication using biometrics."),
                QuizQuestion("q10", "What should you do if your password appears in a known public data breach?", listOf("Ignore it", "Change it immediately across all services where used", "Turn off phone", "Delete browser history"), 1, "Compromised credentials must be changed immediately.")
            )
        ),

        "quiz_network_101" to Quiz(
            id = "quiz_network_101",
            lessonId = "network_101",
            title = "Network Security Assessment",
            passingScorePercentage = 70,
            xpReward = 50,
            questions = listOf(
                QuizQuestion("q1", "What does HTTPS do for your web traffic?", listOf("Makes websites load 10x faster", "Encrypts data between browser and website server", "Deletes web ads", "Blocks phone calls"), 1, "HTTPS encrypts communications between your browser and the website."),
                QuizQuestion("q2", "Why is unencrypted public Wi-Fi risky?", listOf("It drains battery", "Attackers on the network can sniff unencrypted traffic", "It turns off screen lock", "It deletes app files"), 1, "Public Wi-Fi allows attackers to intercept unencrypted HTTP traffic."),
                QuizQuestion("q3", "What is a major benefit of a VPN on untrusted networks?", listOf("Free internet access", "Creates an encrypted tunnel for all network traffic", "Increases screen resolution", "Deletes spam SMS"), 1, "VPNs encrypt data traffic between your device and the VPN server."),
                QuizQuestion("q4", "What is an 'Evil Twin' Wi-Fi attack?", listOf("A virus that doubles files", "A rogue fake Wi-Fi access point set up by attackers to mimic real Wi-Fi", "A double router", "A satellite signal"), 1, "Evil Twin access points trick devices into connecting to malicious hacker routers."),
                QuizQuestion("q5", "Which prefix in the browser address bar confirms transport encryption?", listOf("http://", "https://", "ftp://", "web://"), 1, "'https://' indicates encrypted HTTP Secure connection."),
                QuizQuestion("q6", "What port is standard for secure HTTPS web traffic?", listOf("Port 80", "Port 443", "Port 22", "Port 53"), 1, "Port 443 is standard for encrypted HTTPS traffic."),
                QuizQuestion("q7", "What is a firewall's primary job?", listOf("To cool down the CPU", "To monitor and filter incoming and outgoing network traffic based on rules", "To speed up downloads", "To store passwords"), 1, "Firewalls control network traffic according to security policies."),
                QuizQuestion("q8", "What is DNS (Domain Name System)?", listOf("A virus scanner", "The phonebook of the internet translating domain names to IP addresses", "A battery saver", "A Wi-Fi booster"), 1, "DNS translates human-readable domain names into numerical IP addresses."),
                QuizQuestion("q9", "Can a commercial VPN protect you from visiting malicious phishing websites?", listOf("Yes, completely", "No, VPNs encrypt transit; they do not automatically block sophisticated phishing sites", "VPNs prevent all malware", "VPNs disable passwords"), 1, "VPNs protect transit encryption, not application-level phishing."),
                QuizQuestion("q10", "What is Packet Sniffing?", listOf("Sending emails", "Capturing and logging network packets passing across a network interface", "Compressing files", "Rebooting routers"), 1, "Packet sniffing intercepts network data traffic.")
            )
        ),

        "quiz_web_app_101" to Quiz(
            id = "quiz_web_app_101",
            lessonId = "web_app_101",
            title = "Web Security Assessment",
            passingScorePercentage = 70,
            xpReward = 50,
            questions = listOf(
                QuizQuestion("q1", "What is SQL Injection (SQLi)?", listOf("Injecting hardware memory", "Inserting malicious SQL code into input fields to query databases unauthorized", "Updating web browser", "Formatting hard drives"), 1, "SQLi manipulates backend database queries via unvalidated user inputs."),
                QuizQuestion("q2", "What is Cross-Site Scripting (XSS)?", listOf("Connecting two monitors", "Injecting malicious scripts into web pages viewed by other users", "Downloading APKs", "Using dark mode"), 1, "XSS executes unauthorized scripts in the victim's browser context."),
                QuizQuestion("q3", "What primary defensive coding practice prevents SQL Injection?", listOf("Using short passwords", "Parameterized Queries / Prepared Statements", "Turning off server firewalls", "Increasing screen brightness"), 1, "Prepared statements separate SQL code from user-supplied data input."),
                QuizQuestion("q4", "What does a Session Cookie do?", listOf("Stores browser theme", "Identifies an authenticated user session on the web server", "Speeds up CPU", "Saves photos"), 1, "Session cookies maintain your logged-in state across web page requests."),
                QuizQuestion("q5", "What organization publishes the famous 'Top 10' web security risks list?", listOf("OWASP", "IEEE", "W3C", "ISO"), 0, "OWASP publishes the OWASP Top 10 web vulnerabilities."),
                QuizQuestion("q6", "What is CSRF (Cross-Site Request Forgery)?", listOf("Stealing passwords directly", "Forcing an authenticated user to execute unwanted actions on a web app", "Encrypting database files", "Slow Wi-Fi"), 1, "CSRF tricks a browser into executing unauthorized commands in an active session."),
                QuizQuestion("q7", "Why should sensitive cookies have the 'HttpOnly' flag?", listOf("To make them load faster", "To prevent client-side JavaScript from accessing the cookie (mitigating XSS theft)", "To delete them hourly", "To compress them"), 1, "HttpOnly blocks JavaScript access to cookies, thwarting XSS cookie theft."),
                QuizQuestion("q8", "What is Input Validation?", listOf("Checking typing speed", "Inspecting and sanitizing user input data before processing or storing it", "Rebooting servers", "Updating CSS styles"), 1, "Input validation ensures incoming data conforms to expected safety rules."),
                QuizQuestion("q9", "What is Output Encoding?", listOf("Printing documents", "Escaping special characters before rendering data in browser HTML to prevent XSS", "Compressing videos", "Deleting logs"), 1, "Output encoding neutralizes data rendering as executable script code."),
                QuizQuestion("q10", "What does 'Broken Access Control' mean in web security?", listOf("A broken door lock", "Failure to properly enforce user permission boundaries, allowing unauthorized access", "Slow database queries", "Expired SSL certificates"), 1, "Broken access control allows users to act outside their intended permission roles.")
            )
        ),

        "quiz_mobile_101" to Quiz(
            id = "quiz_mobile_101",
            lessonId = "mobile_101",
            title = "Mobile Security Assessment",
            passingScorePercentage = 70,
            xpReward = 50,
            questions = listOf(
                QuizQuestion("q1", "What is the main risk of sideloading modded APK files from random websites?", listOf("App icons change color", "APKs can contain hidden malware, keyloggers, or spyware", "Battery charges faster", "Storage doubles"), 1, "Third-party APK files can be injected with trojans or spyware."),
                QuizQuestion("q2", "Is it safe for a simple calculator app to request SMS and Camera access?", listOf("Yes, all apps need permissions", "No, it is a major privacy risk and unnecessary for a calculator", "Yes, on weekends", "Only if free"), 1, "Single-purpose apps requesting excessive permissions pose security risks."),
                QuizQuestion("q3", "What is SIM Swapping?", listOf("Changing phone cases", "Tricking mobile carriers into transferring your phone number to a hacker's SIM", "Using dual SIMs", "Upgrading to 5G"), 1, "SIM swapping hijacks your phone number to intercept SMS OTPs."),
                QuizQuestion("q4", "How can you defend your phone number against SIM swapping?", listOf("Set a SIM PIN under phone settings", "Delete all contacts", "Turn off Bluetooth", "Use airplane mode"), 0, "A SIM PIN blocks unauthorized transfer of your mobile line."),
                QuizQuestion("q5", "Where should you manage and revoke app permissions on Android?", listOf("Settings > Privacy / Permission Manager", "Calculator App", "Phone Dialer", "Clock App"), 0, "Android Permission Manager under Settings lets you view and revoke app privileges."),
                QuizQuestion("q6", "Why is enabling a lock screen PIN or Biometric crucial for mobile security?",listOf("It decorates the screen", "It protects stored data and prevents unauthorized physical access if lost", "It speeds up Wi-Fi", "It clears cache"), 1, "Screen locks prevent physical device compromise."),
                QuizQuestion("q7", "What is Android App Sandboxing?", listOf("A sandbox game", "An isolation mechanism ensuring each app runs in its own secure user process space", "A battery saver", "A cloud backup"), 1, "Sandboxing prevents apps from accessing other apps' private data."),
                QuizQuestion("q8", "What is the risk of connecting to unknown Bluetooth devices in public?", listOf("Faster charging", "Bluejacking or unauthorized data access/pairing attacks", "Better audio", "Clearing storage"), 1, "Unsecured Bluetooth pairing can expose device connections."),
                QuizQuestion("q9", "Why are OS and Security updates critical for smartphones?", listOf("They change wallpaper", "They patch newly discovered vulnerabilities before attackers exploit them", "They increase phone weight", "They disable camera"), 1, "Updates close security vulnerabilities."),
                QuizQuestion("q10", "What should you do if your smartphone is lost or stolen?", listOf("Buy a new phone immediately and do nothing", "Remotely locate, lock, or wipe the device using Find My Device / cloud services", "Post on social media", "Turn off Wi-Fi"), 1, "Remote wiping protects your personal data on lost devices.")
            )
        ),

        "quiz_privacy_101" to Quiz(
            id = "quiz_privacy_101",
            lessonId = "privacy_101",
            title = "Digital Privacy Assessment",
            passingScorePercentage = 70,
            xpReward = 50,
            questions = listOf(
                QuizQuestion("q1", "What is Data Minimization?", listOf("Deleting all photos", "Providing only the minimum personal data necessary for a service to function", "Using smaller screens", "Compressing files"), 1, "Data minimization reduces personal data exposure during breaches."),
                QuizQuestion("q2", "Why is posting photos of your flight boarding pass online dangerous?", listOf("Boarding pass barcodes contain personal passenger data and PNR numbers", "Photos expire after 1 hour", "Friends will dislike the photo", "It wastes battery"), 0, "Barcodes on boarding passes reveal passenger name, booking reference (PNR), and flight details."),
                QuizQuestion("q3", "What location setting should you select for non-navigation apps?", listOf("Always Allow Precise Location", "Approximate Location / Only While Using App", "Allow All Time in Background", "Never update app"), 1, "Limiting apps to approximate location reduces tracking while preserving functionality."),
                QuizQuestion("q4", "How can you restrict who sees your social media posts?", listOf("Set Profile Privacy to Private / Friends Only", "Turn on Airplane Mode", "Use dark mode", "Change wallpaper"), 0, "Restricting profile visibility prevents public data harvesting by scammers."),
                QuizQuestion("q5", "What should you do if an imposter profile imitates you on social media?", listOf("Report the fake profile to platform support and warn your contacts", "Do nothing", "Pay money", "Delete your phone"), 0, "Reporting imposter accounts gets them removed and protects your contacts from fraud."),
                QuizQuestion("q6", "What is a Digital Footprint?", listOf("Shoe size in digital form", "The trail of data left behind by users on the internet", "A browser theme", "RAM usage"), 1, "Your digital footprint encompasses all data traces you leave online."),
                QuizQuestion("q7", "Why are third-party advertising trackers privacy risks?", listOf("They slow down CPU", "They build detailed behavioral profiles across different websites without explicit consent", "They delete photos", "They block Wi-Fi"), 1, "Trackers aggregate browsing habits to build invasive user profiles."),
                QuizQuestion("q8", "What is Metadata in digital photographs?", listOf("The image pixels", "Hidden data embedded in image files including camera model, timestamp, and GPS coordinates", "Image color filters", "File compression ratio"), 1, "EXIF metadata can accidentally reveal private location coordinates."),
                QuizQuestion("q9", "How does two-factor authentication protect your privacy?", listOf("It encrypts email text", "It blocks unauthorized account access even if credentials are leaked", "It hides your IP address", "It stops ads"), 1, "2FA prevents unauthorized account compromise."),
                QuizQuestion("q10", "What is Incognito / Private Browsing mode designed to do?", listOf("Make you completely anonymous online", "Not save local browsing history, cookies, or cache on your device", "Block all malware", "Provide free VPN"), 1, "Private browsing clears local session data when windows are closed.")
            )
        ),

        "quiz_upi_101" to Quiz(
            id = "quiz_upi_101",
            lessonId = "upi_101",
            title = "UPI & Payment Fraud Assessment",
            passingScorePercentage = 70,
            xpReward = 50,
            questions = listOf(
                QuizQuestion("q1", "When is your UPI PIN required during a transaction?", listOf("To receive money from a friend", "ONLY to debit/send money from your account", "To claim cashbacks", "To check phone battery"), 1, "Golden Rule: UPI PIN is required ONLY when money leaves your bank account!"),
                QuizQuestion("q2", "Someone on OLX sends a QR code claiming 'Scan QR to receive ₹2,000'. What happens if you scan and enter PIN?", listOf("You receive ₹2,000", "₹2,000 is deducted from YOUR account!", "Nothing happens", "Phone restarts"), 1, "Scanning QR codes and entering PINs is a payment request that deducts money from your account."),
                QuizQuestion("q3", "A caller claims to be customer care and asks you to install 'AnyDesk' or 'TeamViewer'. Why?", listOf("To fix network speed", "To remotely view your screen and steal payment PINs/OTPs", "To gift free money", "To update Android"), 1, "Screen-sharing apps allow scammers to view your banking screens and OTPs in real-time."),
                QuizQuestion("q4", "What should you do if you receive a 'Collect Request' from an unknown UPI ID?", listOf("Approve it immediately", "Decline / Ignore the request", "Enter wrong PIN", "Forward to contacts"), 1, "Decline collect requests from unknown callers or VPAs immediately."),
                QuizQuestion("q5", "What official helpline number should you call in India after financial cyber fraud?", listOf("1930 (National Cyber Crime Helpline)", "100", "101", "102"), 0, "1930 is the official National Cyber Crime Helpline in India for reporting financial fraud swiftly."),
                QuizQuestion("q6", "Can a merchant or buyer ever transfer money into your bank by asking for your UPI PIN?", listOf("Yes, always", "No, entering a UPI PIN always debits (sends) money from your account", "Only on Sundays", "Only via SMS"), 1, "UPI PINs authorize outgoing fund transfers."),
                QuizQuestion("q7", "What is QRLjacking?", listOf("Quick Wi-Fi jack", "A phishing technique where attackers hijack authentication sessions via malicious QR codes", "A charging cable", "A screen lock"), 1, "QRLjacking exploits QR code login flows to hijack accounts."),
                QuizQuestion("q8", "What should you do if you accidentally share your bank OTP or UPI PIN with a scammer?", listOf("Wait 24 hours", "Immediately block your UPI handle, freeze your bank account, and call 1930", "Delete SMS", "Turn off phone"), 1, "Immediate card/UPI blocking prevents further fraudulent withdrawals."),
                QuizQuestion("q9", "Are unexpected lottery or investment winnings asking for upfront fees legitimate?", listOf("Yes, claim them immediately", "No, upfront fee demands for lottery or guaranteed investment returns are always scams", "Only if sent via email", "Only via WhatsApp"), 1, "Advance fee fraud is a classic financial scam."),
                QuizQuestion("q10", "Why should you verify the receiver's name on your UPI app before entering PIN?", listOf("To check phone battery", "To confirm you are sending money to the intended recipient and not an impostor", "To increase speed", "To change theme"), 1, "Always verify recipient name and VPA before authorizing payments.")
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
