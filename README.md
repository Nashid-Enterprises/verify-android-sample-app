**Introduction:**

  

This library encompasses four primary steps: OCR, NFC reader, liveness detection, and document photo comparison, offering a comprehensive solution for extracting data from documents and verifying their authenticity..

  

**Integration Steps:**

  

1.  **Prerequisites:**

  

*   Android 5.0(API level 21) or higher.

  

2.  **Installation:**

*   To install NashidSDK, add the following dependency to your app’s build.gradle file:

```
implementation("com.github.Nashid-Enterprises:verify-android-sdk-public:1.6@aar") {
   setTransitive(true)
}
```
*   Also need to add this below line in main build.gradle/seting.gradle/setting.gradle.kts file

```
repositories {
  maven { url =uri("https://jitpack.io") }
}
```
  

**SDK Features**:

  

1.  **OCR (Optical Character Recognition):**

*   Extracts text information from document images using advanced OCR techniques.
*   Recognizes and extracts crucial data such as names, dates of birth, expiry dates, and identification numbers from documents.
    

2.  **NFC (Near Field Communication) Reader:**

*   Enables seamless reading of data from NFC-enabled documents such as e-passports or identity cards.
*   Facilitates quick and secure retrieval of document information using NFC technology.

3.  **Liveness Detection:**

*   Verifies the liveness of individuals by analyzing facial features and performing biometric checks.
*   Ensures that individuals interacting with the application are physically present, preventing spoofing or fraudulent attempts.  

4.  **Document Photo Comparison:**

*   Compares the liveness photo captured during the verification process with the photo extracted from the document.
*   Enhances security measures by determining if the individual's face matches the photo in the document, thereby preventing identity fraud.

Upon completion, the library provides a comprehensive result containing all extracted document data, including text information, NFC data, liveness verification status, and the outcome of the document photo comparison. This consolidated result is suitable for identity verification or document processing workflows.

  
**Usage Examples:**

Import the dependency

```Import com.kyc.nashidmrz.NashidSDK```

To initialize the NashidSDK and access its functionalities, use the following code snippet:

  
```
NashidSDK.getInstance().init( "YOUR_SDK_TOKEN", "YOUR_COMPANY_UUID", END_POINT_URL, EMAIL, SDKIntListener)
```
  

*   Replace "YOUR_SDK_TOKEN" with the SDK token key obtained from the dashboard (Check screenshot_1).
*   Replace "YOUR_COMPANY_UUID" with the CompanyUUID key obtained from the dashboard. (Check screenshot_1).
*   Replace "END_POINT_URL" with the API base url
*   Replace "EMAIL" with the employee email which you registered on the dashboard.
*   Add the `SDKIntListener` interface to receive callback methods for successful or failed SDK initialization.


The screenshot below illustrates the process: first, navigate to the company dashboard, then select the company option from the left side menu, and finally, click on API keys. This action will open a dialog where you can find the SDK token and companyUUID.
  

![](https://lh7-us.googleusercontent.com/nL5gAQ3QGU82ikxnZYQEanpEMUsqQz3XpY21wXgXMYKUAu3DKct-_HIeXxCa-TXn1Yur76zI0kepkFuDcK7LCs7G9XYRo1shP3DcsTb8zlD0UvvGFIAmQLF_dtsRJqtqNVvFMc8PVBSd4XIbYDD2bEs)

  

**Feature and UI Configuration:**

  

*   setShowInstruction(context : Context , showInstruction : Boolean)
    *  Set whether to display instructions during the verification process.
*   Parameters:
    *  `context:` The context of the Andorid application.
    *  `showInstruction:` Set to true to display instructions; false otherwise.


  

**Getter Methods:**

  

1.  setResultCallback: (listener : ResultListener)?
    *  This ResultListnener provide is an interface and provide 2 callback method
        1.  onResultData(JSONObject jsonObject,String docType)
            *  `jsonObject` have full data of all step which you have done.
            *  `doctype` define which document you scanned, you will get value like E-passport/ID card.
        2.  onFailure()
            * This method will call when unable to get jsonObject.
          
Here is a sample response 

```
{
	"Scan": {
		"Document No": "113414631",
		"Birth Date": "1984-10-01",
		"Expiry Date": "2020-20-01",
		"Name": "RAIS HAIDER",
		"Nationality": "IND",
		"Country": "0MN",
		"Gender": "Male",
		"MRZ text":  "IDOMN1134146310<<<<<<<<<<<<<<<\n8401108M2001207IND<<<<<<<<<<<0\nRAIS<HAIDER<<<<<<<<<<<<<<<<<<<"
	},
	"NFC": {
		"Identity Number": "0113414631",
		"Issue Date": "2018-20-01",
		"Expiry Date": "2020-20-01",
		"Place of Issue (Arabic)": "مسقط",
		"Place of Issue (English)": "Seeb Resident",
		"Full Name (Arabic)": "رئيس حيدر",
		"Full Name (English)": "RAIS HAIDER",
		"Date of Birth": "1984-10-01",
		"Country of Birth (Arabic)": "الهند",
		"Country of Birth (English)": "INDIA",
		"Nationality (Arabic)": "هندي",
		"Gender (Arabic)": "ذكر",
		"Nationality (English)": "INDIAN",
		"Gender (English)": "Male",
		"Visa Number": "60102080",
		"Permit Type": "EMPLOYMENT SINGLE",
		"Use By Date": "2020-25-10",
		"Permit Number": "11751181",
		"Company Name (Arabic)": "مشاريع المازن للشرق الاوسط",
		"Company Name (English)": "ALMAZEM PROJECTS FOR THE MODLE EAST",
		"Company Address (Arabic)": "\nالموالح, السيب\nالصندوق البريدي 366, الرمز البريدي 114"
	},
	"Liveness": {
		"AML screening": true,
		"Liveness Confirmed": false,
		"Match value": "40 %"
	},
	"Images": {
		"before-crop-front": "android.graphics.Bitmap@a7fae65",
		"before-crop-back": "android.graphics.Bitmap@69e373a",
		"after-crop-front": "https://s3api.dev.projectnid.com/cropped-users-imgs/cropped-image-1712290905.422744.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20240405%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240405T042145Z&X-Amz-Expires=18000&X-Amz-SignedHeaders=host&X-Amz-Signature=4d41ff6dca85a5a61179b5ca63a88b43fe54cfe85d562408b757fc2742701417",
		"after-crop-back": "https://s3api.dev.projectnid.com/cropped-users-imgs/cropped-image-1712290908.232141.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20240405%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240405T042148Z&X-Amz-Expires=18000&X-Amz-SignedHeaders=host&X-Amz-Signature=9ecd03d6d9f10a6bbbd91687151681b74c157628ab75864fc89bc5f25432c97a",
		"nfc-image": "android.graphics.Bitmap@70d5feb",
		"liveness-image": "android.graphics.Bitmap@eada148"
	},
	"NFC ": "Supported",
	"Location": {
		"Latitude": 22.3029462,
		"Longitude": 70.7581132,
		"Address": "8Q35+57W, Raiya Rd, behind Alap Green City, Radhika Park, Rajkot, Gujarat 360007, India"
	}
}

```
  
  *Note:* Type: Card/Passport

  

2.  documentScan(activity: Activity)

*   Open the ID card scan module.
*   Parameters:
    *   `activity:` The context of the Andorid application.

3.  passportScan(activity: Activity)

*   Open the Passport scan module.
*   Parameters:
    *   `activity:` The context of the Andorid application.

4.  isShowInstruction(context: Context)

*   Check whether instructions are enabled during the verification process.
*   Parameters:
    *    `activity:` The context of the Andorid application.

  

To configure feature and UI settings, utilize the NashidSDK class as demonstrated in the examples provided.

  

**Here is sample app:** https://github.com/Nashid-Enterprises/verify-ios-sample-app
