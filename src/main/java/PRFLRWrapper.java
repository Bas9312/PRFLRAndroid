import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

public class PRFLRWrapper extends Application{
	private PRFLRWrapper() {
	}
	
	public static void init(Context c) throws Exception {
		ApplicationInfo inf = c.getPackageManager().getApplicationInfo(c.getPackageName(),PackageManager.GET_META_DATA);
		String apiKey = inf.metaData.getString("apiKey");
		String source = PRFLRWrapper.getDeviceName();
		PRFLR.init(source, apiKey);
	}
    public static void setOveflowCounter(int value) {
        PRFLR.overflowCount = value;
    }
	public static Boolean begin(String timerName) throws Exception {
		return PRFLR.begin(timerName);
	}
	
	public static Boolean end(String timerName, String info) throws Exception {
		return PRFLR.end(timerName, info);
	}
	private static String getDeviceName() {
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		String version = Build.VERSION.RELEASE;
		if (model.startsWith(manufacturer)) {
			return model + " " + version;
		} else {
			return manufacturer + " " + model + " " + version;
		}
	}
}
