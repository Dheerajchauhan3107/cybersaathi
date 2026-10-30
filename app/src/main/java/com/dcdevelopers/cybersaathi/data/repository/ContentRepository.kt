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
        CourseCertificate("cert_python", "course_python", "Python for Cybersecurity", "Programming", "quiz_python_101", null),
        CourseCertificate("cert_networking", "course_networking", "Networking Fundamentals", "Networking", "quiz_network_101", "lab_url"),
        CourseCertificate("cert_linux", "course_linux", "Linux and System Security", "System Security", "quiz_linux_101", null),
        CourseCertificate("cert_web", "course_web", "Web Development & Web Security", "Web Security", "quiz_web_app_101", null),
        CourseCertificate("cert_fundamentals", "course_fundamentals", "Cybersecurity Fundamentals", "Fundamentals", "quiz_fundamentals_101", null),
        CourseCertificate("cert_forensics", "course_forensics", "Digital Forensics & Incident Response", "Forensics", "quiz_forensics_101", null)
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
        // Course 1: Python
        Lesson(
            id = "python_101",
            title = "Python Defensive Scripting & Log Parsing",
            subtitle = "CSV log handling, regex matching, exception handling & input validation",
            category = "Python",
            durationMinutes = 8,
            difficulty = "Intermediate",
            iconName = "Code",
            sections = listOf(
                LessonSection(
                    title = "Python Fundamentals for Security",
                    bodyText = "Python is the premier language for cybersecurity automation, log parsing, and security operations. Key data structures include lists, dictionaries, and sets for tracking active IP addresses or failed login attempts.",
                    warningTip = "Always handle untrusted file inputs with exceptions (try-except) to prevent script crashes on malformed data.",
                    practicalExample = "failed_ips = set()\nwith open('server.log') as f:\n  for line in f:\n    if '401 Unauthorized' in line:\n      failed_ips.add(line.split()[0])"
                ),
                LessonSection(
                    title = "Safe CSV Log Parsing & Regular Expressions",
                    bodyText = "Security logs often arrive in CSV or syslog format. Parsing logs requires safe string manipulation or regex (import re) to extract IP addresses, timestamps, and HTTP status codes safely without executing malicious payloads embedded in User-Agent strings.",
                    warningTip = "Never pass raw unvalidated log inputs directly into system shell calls (os.system / subprocess).",
                    practicalExample = "Regex pattern for IPv4 extraction: r'\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b'"
                )
            ),
            quizId = "quiz_python_101",
            labId = null
        ),

        // Course 2: Networking
        Lesson(
            id = "network_101",
            title = "Networking Fundamentals & DNS Security",
            subtitle = "OSI/TCP-IP models, DNS record types, TLS handshake & firewall rules",
            category = "Networking",
            durationMinutes = 9,
            difficulty = "Intermediate",
            iconName = "Wifi",
            sections = listOf(
                LessonSection(
                    title = "OSI & TCP/IP Network Models",
                    bodyText = "The OSI 7-layer model (Physical, Data Link, Network, Transport, Session, Presentation, Application) maps network communication. TCP (Transmission Control Protocol) guarantees reliable packet delivery via a 3-way handshake (SYN, SYN-ACK, ACK), while UDP prioritizes low-latency streaming.",
                    warningTip = "HTTP operates unencrypted over TCP port 80. HTTPS uses TLS encryption over port 443.",
                    practicalExample = "Wireshark packet captures display TCP sequence numbers, IP headers, and payload data."
                ),
                LessonSection(
                    title = "DNS Resolution & TLS Certificate Validation",
                    bodyText = "DNS (Domain Name System) translates human domains (e.g. google.com) to IP addresses using A, AAAA, CNAME, and MX records. During an HTTPS connection, the browser verifies that the server's TLS certificate matches the exact hostname resolved by DNS.",
                    warningTip = "If DNS resolution succeeds but TLS hostname validation fails, do NOT trust the site! It indicates a potential Man-in-the-Middle or spoofing attack.",
                    practicalExample = "Checking certificate subject alternative names (SAN) in Chrome Developer Tools."
                )
            ),
            quizId = "quiz_network_101",
            labId = "lab_url"
        ),

        // Course 3: Linux
        Lesson(
            id = "linux_101",
            title = "Linux Security & Permission Model",
            subtitle = "Owner/Group/Other permissions, least privilege, sudo & SSH hardening",
            category = "Linux",
            durationMinutes = 8,
            difficulty = "Intermediate",
            iconName = "Terminal",
            sections = listOf(
                LessonSection(
                    title = "Linux Permission Bit Structure",
                    bodyText = "Linux enforces access control through Owner (u), Group (g), and Others (o) permission bits for Read (r=4), Write (w=2), and Execute (x=1).\n\nFor example, 'chmod 640 config.ini' grants Read/Write to the owner, Read to group members, and Zero access to others.",
                    warningTip = "Never set file permissions to '777' (world-writable). Grant only minimum required privileges.",
                    practicalExample = "'ls -l /etc/shadow' shows '-rw-r----- 1 root shadow', restricting sensitive password hashes."
                ),
                LessonSection(
                    title = "SSH Hardening & Service Privilege Isolation",
                    bodyText = "System services should run under isolated non-root user accounts. SSH servers should be hardened by disabling root login ('PermitRootLogin no') and enforcing key-based authentication ('PasswordAuthentication no').",
                    warningTip = "Services only requiring read access to a config file should NEVER be granted root or write access.",
                    practicalExample = "Key-based SSH using ED25519 keys ('ssh-keygen -t ed25519') provides strong cryptographic access."
                )
            ),
            quizId = "quiz_linux_101",
            labId = null
        ),

        // Course 4: Web
        Lesson(
            id = "web_app_101",
            title = "Web Development & Web Security",
            subtitle = "HTML/CSS/JS security, sessions, SQL Injection & XSS prevention",
            category = "Web Security",
            durationMinutes = 9,
            difficulty = "Intermediate",
            iconName = "Code",
            sections = listOf(
                LessonSection(
                    title = "HTML/JS Client-Side Limitations",
                    bodyText = "Client-side HTML form validation or JavaScript input checks improve user experience but provide ZERO security against attackers who bypass the browser using tools like Curl or Burp Suite. All security validation MUST occur on the server side.",
                    warningTip = "Never rely on client-side JavaScript checks as a security boundary.",
                    practicalExample = "Disabling 'required' or 'pattern' attributes in browser dev tools bypasses client-side form checks."
                ),
                LessonSection(
                    title = "SQL Injection & Parameterized Queries",
                    bodyText = "SQL Injection occurs when unvalidated user inputs are concatenated directly into database queries. The ultimate defense is Parameterized Queries (Prepared Statements), which treat user parameters as literal data values rather than executable code.",
                    warningTip = "Using parameterized queries defends against SQLi, but login rate-limiting is still required to prevent brute-force credential stuffing.",
                    practicalExample = "Prepared Statement: PreparedStatement stmt = conn.prepareStatement(\"SELECT * FROM users WHERE u=? AND p=?\")"
                ),
                LessonSection(
                    title = "XSS Prevention & Context-Aware Encoding",
                    bodyText = "Cross-Site Scripting (XSS) occurs when untrusted data is rendered directly into HTML. Context-Aware Output Encoding (converting '<' to '&lt;', '>' to '&gt;') prevents browsers from interpreting user strings as executable script tags.",
                    warningTip = "Store sensitive session cookies with 'HttpOnly' and 'SameSite=Strict' flags to mitigate XSS theft.",
                    practicalExample = "HTML Encoding '<script>' renders safe text '&lt;script&gt;' instead of executing code."
                )
            ),
            quizId = "quiz_web_app_101",
            labId = null
        ),

        // Course 5: Fundamentals
        Lesson(
            id = "fundamentals_101",
            title = "Cybersecurity Fundamentals",
            subtitle = "CIA triad, threat modeling, risk formulas & defense in depth",
            category = "Fundamentals",
            durationMinutes = 7,
            difficulty = "Beginner",
            iconName = "Shield",
            sections = listOf(
                LessonSection(
                    title = "The CIA Triad Principles",
                    bodyText = "The foundation of security rests on Confidentiality (data privacy), Integrity (data accuracy/tamper-proofing), and Availability (system uptime). Risk is evaluated as: Risk = Threat Probability * Vulnerability Severity * Impact.",
                    warningTip = "A failure in any single pillar compromises the entire system security posture.",
                    practicalExample = "Encryption ensures Confidentiality. Digital signatures ensure Integrity. Multi-region servers ensure Availability."
                ),
                LessonSection(
                    title = "Defense in Depth & Least Privilege",
                    bodyText = "Defense in Depth employs overlapping security layers (Firewall + 2FA + Disk Encryption + Endpoint Antivirus) so if one fails, others block the intrusion.",
                    warningTip = "Never run everyday user accounts with full Administrator privileges.",
                    practicalExample = "Using Screen Lock + App Lock + 2FA + Device Encryption on smartphones."
                )
            ),
            quizId = "quiz_fundamentals_101",
            labId = null
        ),

        // Course 6: Forensics
        Lesson(
            id = "forensics_101",
            title = "Digital Forensics & Incident Response",
            subtitle = "Evidence preservation, log analysis, timeline analysis & chain of custody",
            category = "Forensics",
            durationMinutes = 8,
            difficulty = "Intermediate",
            iconName = "Search",
            sections = listOf(
                LessonSection(
                    title = "Evidence Integrity & Chain of Custody",
                    bodyText = "Digital forensics demands strict preservation of evidence. Forensic analysts NEVER analyze original storage drives directly—they create cryptographic bit-stream copies (images) and verify SHA-256 hashes to guarantee evidence integrity.",
                    warningTip = "Document every person who handles physical/digital evidence to maintain legal Chain of Custody.",
                    practicalExample = "Computing SHA-256 hash before and after analysis: if hashes match identically, evidence was not altered."
                ),
                LessonSection(
                    title = "Log Timeline Reconstruction",
                    bodyText = "When investigating security incidents, analysts cross-examine timestamps from web logs, firewall logs, and authentication servers to reconstruct the attacker's timeline.",
                    warningTip = "Ensure all servers synchronize time using Network Time Protocol (NTP) to align log timestamps.",
                    practicalExample = "Matching a failed SSH login log at 03:00 AM with a subsequent sudo privilege escalation at 03:02 AM."
                )
            ),
            quizId = "quiz_forensics_101",
            labId = null
        ),

        // Phishing
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
                    bodyText = "1. Sender Domain Verification: Inspect the actual email domain address, not just the display name.\n2. Artificial Urgency: Threatening account suspension within 1-2 hours to rush decisions.\n3. Lookalike URLs: Misspelled domains like 'amaz0n-security.com' or 'paypaI.com' (using capital 'I').",
                    warningTip = "Legitimate institutions will never demand passwords or OTPs over SMS or email.",
                    practicalExample = "An email claiming to be from Netflix arriving from 'billing@netflix-account-update.xyz'."
                )
            ),
            quizId = "quiz_phishing_101",
            labId = "lab_phishing"
        ),

        // UPI
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
                    title = "Payment Scams & Cybercrime Laws in India",
                    bodyText = "Under India's Information Technology Act 2000 (Sections 66C, 66D) and applicable provisions of the Bharatiya Nyaya Sanhita (BNS) 2023, identity theft and cheating by personation using computer resources are major offences.\n\nReport financial cyber fraud immediately to National Cyber Crime Helpline 1930.",
                    warningTip = "Never install screen-sharing apps (AnyDesk, TeamViewer) on instructions from unknown callers.",
                    practicalExample = "Decline collect requests from unknown VPAs and report financial cyber fraud to helpline 1930."
                )
            ),
            quizId = "quiz_upi_101",
            labId = "lab_upi"
        )
    )

    val courses = listOf(
        Course(
            id = "course_python",
            title = "Python for Cybersecurity",
            description = "Master defensive scripting, CSV/json log parsing, regular expressions, and security automation.",
            iconName = "Code",
            category = "Programming",
            modules = listOf(
                CourseModule("mod_py_1", "Python Foundations & Program Flow", "Syntax, variables, loops, functions, and defensive data structures.", listOf("python_101"))
            ),
            quizId = "quiz_python_101",
            labId = null,
            certificateId = "cert_python"
        ),
        Course(
            id = "course_networking",
            title = "Networking Fundamentals",
            description = "Understand OSI/TCP-IP models, IP addressing, DNS resolution, TLS certificates, and network defense.",
            iconName = "Wifi",
            category = "Networking",
            modules = listOf(
                CourseModule("mod_net_1", "Networking Basics & Protocols", "DNS resolution, TLS handshake, ports, and Wi-Fi security.", listOf("network_101"))
            ),
            quizId = "quiz_network_101",
            labId = "lab_url",
            certificateId = "cert_networking"
        ),
        Course(
            id = "course_linux",
            title = "Linux and System Security",
            description = "Explore filesystem permissions, least privilege, process isolation, SSH hardening, and logs.",
            iconName = "Terminal",
            category = "System Security",
            modules = listOf(
                CourseModule("mod_lin_1", "Linux Permissions & Administration", "Owner/Group/Other bits, sudo least privilege, and SSH hardening.", listOf("linux_101"))
            ),
            quizId = "quiz_linux_101",
            labId = null,
            certificateId = "cert_linux"
        ),
        Course(
            id = "course_web",
            title = "Web Development & Web Security",
            description = "Learn HTML/JS security limits, session cookies, SQL injection prevention, and XSS defense.",
            iconName = "Code",
            category = "Web Security",
            modules = listOf(
                CourseModule("mod_web_1", "Web Vulnerabilities & Defense", "Parameterized queries, XSS encoding, and HttpOnly cookies.", listOf("web_app_101"))
            ),
            quizId = "quiz_web_app_101",
            labId = null,
            certificateId = "cert_web"
        ),
        Course(
            id = "course_fundamentals",
            title = "Cybersecurity Fundamentals",
            description = "Master the CIA triad, threat modeling, defense in depth, passphrases, and security controls.",
            iconName = "Shield",
            category = "Fundamentals",
            modules = listOf(
                CourseModule("mod_fund_1", "Core Concepts & Threat Defense", "CIA triad, defense in depth, and passphrase vaulting.", listOf("fundamentals_101", "phishing_101"))
            ),
            quizId = "quiz_fundamentals_101",
            labId = "lab_phishing",
            certificateId = "cert_fundamentals"
        ),
        Course(
            id = "course_forensics",
            title = "Digital Forensics & Incident Response",
            description = "Learn evidence integrity, SHA-256 hashing, log timeline reconstruction, and chain of custody.",
            iconName = "Search",
            category = "Forensics",
            modules = listOf(
                CourseModule("mod_for_1", "Forensic Investigation & Log Triage", "Chain of custody, hash verification, and timeline analysis.", listOf("forensics_101"))
            ),
            quizId = "quiz_forensics_101",
            labId = null,
            certificateId = "cert_forensics"
        )
    )

    val quizzes = mapOf<String, Quiz>(
        "quiz_python_101" to Quiz(
            id = "quiz_python_101",
            lessonId = "python_101",
            title = "Python Defensive Scripting Assessment",
            passingScorePercentage = 70,
            xpReward = 50,
            questions = listOf(
                QuizQuestion("q1", "A Python security log parser encounters malformed timestamps in a log file. What is the correct defensive programming approach?", listOf("Ignore errors and execute code", "Validate input, handle parsing exceptions safely with try-except, and log errors without crashing", "Delete the log file", "Convert malformed strings to integers"), 1, "Defensive parsers must safely catch input exceptions without crashing or misinterpreting records."),
                QuizQuestion("q2", "Which regex pattern correctly matches a standard IPv4 address string (e.g. 192.168.1.1)?", listOf("r'\\d+\\.\\d+'", "r'\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b'", "r'[a-z]+'", "r'http://\\w+'"), 1, "The regex pattern matches 4 octets separated by dots."),
                QuizQuestion("q3", "Why should raw unvalidated string inputs from log files NEVER be passed directly into Python os.system()?", listOf("It uses too much RAM", "It introduces Command Injection vulnerabilities", "It deletes Python libraries", "It changes dark mode"), 1, "Executing unvalidated user strings via shell commands allows attackers to run arbitrary system commands."),
                QuizQuestion("q4", "Which Python data structure provides O(1) average time complexity for checking unique IP addresses?", listOf("List", "Set", "Tuple", "String"), 1, "Sets store unique elements with fast O(1) lookup times."),
                QuizQuestion("q5", "What module in Python provides built-in CSV file reading capabilities?", listOf("sys", "csv", "os", "http"), 1, "Python's built-in csv module parses comma-separated values safely."),
                QuizQuestion("q6", "How should sensitive API keys or database passwords be managed in Python security scripts?", listOf("Hardcoded directly in script source code", "Loaded from environment variables or secure configuration stores", "Written in public comments", "Printed to stdout"), 1, "Credentials should never be hardcoded; load them from environment variables."),
                QuizQuestion("q7", "What is the purpose of Python's try-except block when reading log files?", listOf("To speed up execution", "To gracefully handle missing files or corrupted line formats", "To clear disk storage", "To disable network connections"), 1, "try-except blocks catch runtime file/format exceptions gracefully."),
                QuizQuestion("q8", "Which function converts a JSON string into a Python dictionary?", listOf("json.dumps()", "json.loads()", "json.open()", "json.read()"), 1, "json.loads() deserializes a JSON string into a Python object/dictionary."),
                QuizQuestion("q9", "Why is opening files using the with statement (with open(...) as f:) recommended in Python?", listOf("It encrypts the file", "It automatically closes the file handle when finished, even if exceptions occur", "It speeds up network bandwidth", "It requires admin permissions"), 1, "The with statement guarantees proper file resource cleanup."),
                QuizQuestion("q10", "What Python library is standard for making HTTP requests during security automation?", listOf("requests / urllib", "math", "random", "tkinter"), 0, "requests or urllib handles HTTP/HTTPS communications.")
            )
        ),

        "quiz_network_101" to Quiz(
            id = "quiz_network_101",
            lessonId = "network_101",
            title = "Networking & DNS Security Assessment",
            passingScorePercentage = 70,
            xpReward = 50,
            questions = listOf(
                QuizQuestion("q1", "DNS resolution succeeds for bank.com, but TLS certificate hostname validation fails. What is the correct defensive decision?", listOf("Proceed to log in because DNS worked", "Stop and investigate the identity mismatch; a failed TLS hostname match indicates potential spoofing/MitM", "Disable HTTPS", "Clear browser cache"), 1, "Never ignore TLS hostname validation failures; a mismatch indicates potential traffic interception."),
                QuizQuestion("q2", "What does HTTPS do for web traffic?", listOf("Deletes web ads", "Encrypts data between browser and server using TLS", "Speeds up CPU", "Blocks calls"), 1, "HTTPS encrypts communications using Transport Layer Security."),
                QuizQuestion("q3", "Why is unencrypted public Wi-Fi risky?", listOf("It drains battery", "Attackers on the network can sniff unencrypted traffic", "It turns off lock screen", "It deletes apps"), 1, "Public Wi-Fi allows attackers to perform packet sniffing on unencrypted HTTP traffic."),
                QuizQuestion("q4", "What is a major benefit of a VPN on untrusted networks?", listOf("Free internet access", "Creates an encrypted tunnel for all network traffic", "Increases resolution", "Deletes spam"), 1, "VPNs encrypt device network traffic via a secure tunnel."),
                QuizQuestion("q5", "What is an 'Evil Twin' Wi-Fi attack?", listOf("A file virus", "A rogue fake access point set up by attackers to mimic real Wi-Fi", "A double router", "A satellite signal"), 1, "Evil Twin routers trick victim devices into connecting to hacker-controlled networks."),
                QuizQuestion("q6", "Which port is standard for secure HTTPS traffic?", listOf("80", "443", "22", "53"), 1, "Port 443 is standard for encrypted HTTPS traffic."),
                QuizQuestion("q7", "What is a firewall's primary job?", listOf("Cool CPU", "Filter incoming and outgoing network traffic based on rules", "Speed up downloads", "Store passwords"), 1, "Firewalls control network traffic flows according to security policies."),
                QuizQuestion("q8", "What is DNS (Domain Name System)?", listOf("Virus scanner", "Translates human-readable domain names into numerical IP addresses", "Battery saver", "Wi-Fi booster"), 1, "DNS translates domain names like google.com into IP addresses."),
                QuizQuestion("q9", "Can a commercial VPN protect you from visiting malicious phishing websites?", listOf("Yes completely", "No; VPNs encrypt transit data, but do not inherently block application-level phishing portals", "VPNs prevent all malware", "VPNs disable passwords"), 1, "VPNs protect transit encryption, not application-level phishing sites."),
                QuizQuestion("q10", "What protocol operates reliably via a 3-way handshake (SYN, SYN-ACK, ACK)?", listOf("UDP", "TCP", "ICMP", "ARP"), 1, "TCP establishes connection reliability via a 3-way handshake.")
            )
        ),

        "quiz_linux_101" to Quiz(
            id = "quiz_linux_101",
            lessonId = "linux_101",
            title = "Linux & System Security Assessment",
            passingScorePercentage = 70,
            xpReward = 50,
            questions = listOf(
                QuizQuestion("q1", "A Linux background service only needs to read a configuration file. What is the correct permission decision under Least Privilege?", listOf("Grant world-writable 777 permission", "Grant minimum read-only permission to the specific user/group and deny write access to others", "Run the service as root", "Disable firewall"), 1, "Least privilege requires granting only the minimum necessary read access."),
                QuizQuestion("q2", "What permission mode is set by 'chmod 640 config.ini'?", listOf("Owner read/write, Group read, Others no access", "World writable", "Owner execute only", "Group full access"), 0, "640 sets Read/Write (6) for owner, Read (4) for group, None (0) for others."),
                QuizQuestion("q3", "Why should direct SSH root login be disabled ('PermitRootLogin no') in Linux hardening?", listOf("It uses too much RAM", "It forces administrators to log in as specific accountable users before using sudo", "It blocks SSH keys", "It slows down CPU"), 1, "Disabling root login enforces individual user accountability."),
                QuizQuestion("q4", "What command displays running processes in Linux?", listOf("ls", "ps / top", "cat", "chmod"), 1, "ps or top monitors active processes."),
                QuizQuestion("q5", "Where are sensitive password hashes stored in modern Linux systems?", listOf("/etc/passwd", "/etc/shadow", "/tmp", "/var/log"), 1, "/etc/shadow securely stores password hashes with restricted root access."),
                QuizQuestion("q6", "What does the 'sudo' command allow an authorized user to do?", listOf("Delete network drivers", "Execute a command with elevated security privileges", "Restart router", "Format BIOS"), 1, "sudo grants temporary elevated administrative privileges."),
                QuizQuestion("q7", "Which SSH key type offers high modern cryptographic security with small key size?", listOf("DES", "ED25519", "MD5", "RC4"), 1, "ED25519 provides strong elliptic-curve security."),
                QuizQuestion("q8", "Where are system authentication logs stored on Debian/Ubuntu Linux systems?", listOf("/var/log/auth.log or journalctl", "/home/user", "/bin/log", "/etc/hosts"), 0, "/var/log/auth.log records login and authentication events."),
                QuizQuestion("q9", "What is the security risk of making files world-writable ('chmod 777')?", listOf("No risk", "Any unprivileged user or compromised local service can modify or corrupt the file", "It locks the file", "It uses more disk space"), 1, "World-writable files allow unauthorized modification by any local user or app process."),
                QuizQuestion("q10", "What package management tool is standard on Debian/Ubuntu systems for applying security updates?", listOf("apt / apt-get", "rpm", "pacman", "brew"), 0, "apt manages system software updates on Debian/Ubuntu.")
            )
        ),

        "quiz_web_app_101" to Quiz(
            id = "quiz_web_app_101",
            lessonId = "web_app_101",
            title = "Web Development & Security Assessment",
            passingScorePercentage = 70,
            xpReward = 50,
            questions = listOf(
                QuizQuestion("q1", "A web application uses parameterized queries against SQL Injection but has no login rate limiting. What is the correct security conclusion?", listOf("The application is completely secure against all login attacks", "SQLi defenses do NOT eliminate authentication abuse risks like brute-force credential stuffing", "Parameterized queries stop brute force", "Rate limiting is useless"), 1, "Security defenses are distinct; SQLi defense does not block brute-force credential stuffing."),
                QuizQuestion("q2", "What is SQL Injection (SQLi)?", listOf("Hardware memory injection", "Inserting malicious SQL commands into inputs to manipulate database queries", "Updating browser", "Formatting hard drive"), 1, "SQLi manipulates backend database queries via unvalidated inputs."),
                QuizQuestion("q3", "What is Cross-Site Scripting (XSS)?", listOf("Connecting two screens", "Injecting malicious scripts into web pages executed in other users' browsers", "Downloading APKs", "Using dark mode"), 1, "XSS executes unauthorized scripts inside victim browsers."),
                QuizQuestion("q4", "What primary defensive coding practice prevents SQL Injection?", listOf("Using short passwords", "Parameterized Queries / Prepared Statements", "Turning off firewalls", "Increasing screen brightness"), 1, "Prepared statements separate SQL commands from user input data."),
                QuizQuestion("q5", "Why should client-side JavaScript form validation never be relied on as a primary security control?", listOf("It is too slow", "Attackers can bypass browser JS checks completely using direct HTTP tools like Curl or Burp Suite", "JavaScript cannot validate text", "HTML forms are obsolete"), 1, "Client-side validation can be bypassed easily; server-side validation is mandatory."),
                QuizQuestion("q6", "Why should sensitive session cookies have the 'HttpOnly' flag?", listOf("To load faster", "To block client-side JavaScript from reading the cookie, mitigating XSS cookie theft", "To delete them hourly", "To compress them"), 1, "HttpOnly blocks JavaScript access to cookies, mitigating session hijacking via XSS."),
                QuizQuestion("q7", "What is Context-Aware Output Encoding designed to prevent?", listOf("SQL Injection", "Cross-Site Scripting (XSS)", "DDoS attacks", "Hard drive failure"), 1, "Output encoding neutralizes user strings so browsers do not execute them as HTML/JS code."),
                QuizQuestion("q8", "What header allows web servers to declare acceptable content sources to browser clients?", listOf("Content-Security-Policy (CSP)", "Server-Timing", "User-Agent", "Accept-Encoding"), 0, "CSP restricts unauthorized script execution and resources."),
                QuizQuestion("q9", "What does a Session Cookie do?", listOf("Stores theme", "Identifies an authenticated user session on the server", "Speeds up CPU", "Saves photos"), 1, "Session cookies maintain your logged-in session state."),
                QuizQuestion("q10", "What organization publishes the famous OWASP Top 10 web vulnerabilities list?", listOf("OWASP", "IEEE", "W3C", "ISO"), 0, "OWASP publishes the OWASP Top 10 web application security risks.")
            )
        ),

        "quiz_fundamentals_101" to Quiz(
            id = "quiz_fundamentals_101",
            lessonId = "fundamentals_101",
            title = "Cybersecurity Fundamentals Assessment",
            passingScorePercentage = 70,
            xpReward = 50,
            questions = listOf(
                QuizQuestion("q1", "What three core principles form the foundational CIA Triad?", listOf("Code, Internet, Access", "Confidentiality, Integrity, Availability", "Cookies, IP, Accounts", "Cipher, Ingress, Authorization"), 1, "The CIA Triad stands for Confidentiality, Integrity, and Availability."),
                QuizQuestion("q2", "How is security risk conceptually modeled?", listOf("Risk = Speed * Memory", "Risk = Threat Probability * Vulnerability Severity * Impact", "Risk = Users / Passwords", "Risk = Downloads + Uploads"), 1, "Risk is the product of Threat probability, Vulnerability severity, and Impact."),
                QuizQuestion("q3", "What does the Principle of Least Privilege dictate?", listOf("Give users maximum admin rights", "Grant users and apps only the minimum access necessary for their tasks", "Allow public access to databases", "Use short passwords"), 1, "PoLP ensures users and applications have only minimal access needed to perform their jobs."),
                QuizQuestion("q4", "What is Defense in Depth?", listOf("Using only one strong password", "Using multiple overlapping layers of security controls", "Storing backups in cloud only", "Turning off Wi-Fi"), 1, "Defense in depth uses overlapping security layers so if one fails, others block the attack."),
                QuizQuestion("q5", "Which of the following represents a system Vulnerability?", listOf("An unpatched software security flaw", "A hacker sitting in a café", "A data breach incident", "An antivirus scanner"), 0, "A vulnerability is a weakness or flaw in software, hardware, or procedures."),
                QuizQuestion("q6", "What is the primary purpose of data encryption?", listOf("Compress file sizes", "Ensure Confidentiality by rendering data unreadable without the decryption key", "Speed up server responses", "Delete malware"), 1, "Encryption protects Confidentiality against unauthorized interception."),
                QuizQuestion("q7", "Which control protects data Integrity?", listOf("Multi-region server power", "Cryptographic digital signatures and checksums", "Public IP addresses", "Firewall packet filtering"), 1, "Cryptographic signatures and hashes verify data has not been tampered with."),
                QuizQuestion("q8", "What ensures Availability in the CIA triad?", listOf("Redundant backups, fault-tolerant servers, and DDoS protection", "Strong master passwords", "Secret encryption keys", "Closing firewall ports"), 0, "Availability ensures systems and data remain operational and accessible."),
                QuizQuestion("q9", "What is an attack surface?", listOf("The physical weight of a server", "The sum of all vulnerable points where an unauthorized attacker can attempt entry", "Network router speed", "Phone screen brightness"), 1, "The attack surface represents all entry points susceptible to exploitation."),
                QuizQuestion("q10", "Why is security awareness training essential for human users?", listOf("Humans are frequently targeted as the entry link via social engineering", "It makes computers run faster", "It replaces antivirus software", "It generates bandwidth"), 0, "Security awareness mitigates human social engineering risks.")
            )
        ),

        "quiz_forensics_101" to Quiz(
            id = "quiz_forensics_101",
            lessonId = "forensics_101",
            title = "Digital Forensics & Incident Response Assessment",
            passingScorePercentage = 70,
            xpReward = 50,
            questions = listOf(
                QuizQuestion("q1", "A forensic analyst must examine a digital storage device containing critical evidence. What is the correct general practice?", listOf("Work directly on the original drive", "Preserve the original, document handling, create a forensic bit-stream image, verify cryptographic hashes, and work on a verified copy", "Format the drive first", "Delete system logs"), 1, "Forensic investigators work on cryptographically verified forensic copies to preserve original evidence integrity."),
                QuizQuestion("q2", "What is Chain of Custody in digital forensics?", listOf("A chain lock on server rooms", "Detailed chronological documentation tracking the collection, handling, custody, and transfer of evidence", "A sequence of passwords", "Network wiring topology"), 1, "Chain of custody proves evidence was tracked and un-tampered with legally."),
                QuizQuestion("q3", "Why are SHA-256 hashes computed before and after forensic disk imaging?", listOf("To compress disk storage", "To prove that the forensic copy is an exact identical match to the original drive", "To encrypt the OS", "To speed up scanning"), 1, "Matching cryptographic hashes prove data integrity."),
                QuizQuestion("q4", "What protocol synchronizes system time across network servers to ensure log timestamps align during incident response?", listOf("NTP (Network Time Protocol)", "HTTP", "FTP", "ARP"), 0, "NTP synchronizes clocks across servers so log timestamps align."),
                QuizQuestion("q5", "What is the first phase in standard Incident Response after an intrusion is detected?", listOf("Eradication", "Containment / Triage", "Buying new servers", "Publishing press releases"), 1, "Triage and Containment isolate systems to limit breach damage."),
                QuizQuestion("q6", "Why should system logs be forwarded to a central SIEM server in real time?", listOf("To fill up hard drives", "To prevent local attackers from deleting or altering audit logs on compromised hosts", "To speed up browsing", "To update antivirus"), 1, "Centralized logging prevents attackers from covering their tracks on compromised machines."),
                QuizQuestion("q7", "What is volatility order in digital evidence collection?", listOf("Collecting static hard drives first", "Collecting short-lived evidence (RAM, CPU cache, network connections) before powered-down storage", "Sorting files by name", "Deleting temporary files"), 1, "Volatile data like RAM vanishes when power is cut and must be collected first."),
                QuizQuestion("q8", "What is an Indicator of Compromise (IOC)?", listOf("A new computer monitor", "Artifacts or log evidence indicating a potential security breach (e.g. malicious IP, bad file hash)", "A network cable", "A strong password"), 1, "IOCs are evidence signatures indicating compromise."),
                QuizQuestion("q9", "What is the purpose of an Incident Response Post-Mortem (Lessons Learned) review?", listOf("To assign personal blame", "To analyze breach causes, evaluate response effectiveness, and update security controls", "To sell equipment", "To lower salaries"), 1, "Lessons learned reviews improve security posture for future incidents."),
                QuizQuestion("q10", "What legal consideration governs handling employee personal data during forensic investigations?", listOf("No rules apply", "Applicable data protection laws and corporate investigation privacy policies", "Only local weather", "Game guidelines"), 1, "Investigations must comply with applicable privacy and data protection laws.")
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
