package editor.configurator;

import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Orsolya Dekany
 */
@Component(
	immediate = true,
	property = {
        "editor.name=alloyeditor",
        "service.ranking:Integer=1000"
	},

        service = EditorConfigContributor.class
)
public class AlloyEditorButtonConfigurator extends BaseEditorConfigContributor {

	public void populateConfigJSONObject(
		JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
		ThemeDisplay themeDisplay,
		RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

		JSONObject toolbarsJSONObject = jsonObject.getJSONObject("toolbars");

		if (toolbarsJSONObject == null) {
			return;
		}

		JSONObject stylesToolbarJSONObject = toolbarsJSONObject.getJSONObject(
			"styles");

		if (stylesToolbarJSONObject == null) {
			return;
		}

		JSONArray selectionsJSONArray = stylesToolbarJSONObject.getJSONArray(
			"selections");

		if (selectionsJSONArray == null) {
			return;
		}

		for (int i = 0; i < selectionsJSONArray.length(); i++) {
			JSONObject selectionJSONObject = selectionsJSONArray.getJSONObject(
				i);

			JSONArray buttonsJSONArray = selectionJSONObject.getJSONArray(
				"buttons");

			buttonsJSONArray.put("paragraphLeft")
			.put("paragraphCenter")
			.put("paragraphRight");

			selectionJSONObject.put("buttons", buttonsJSONArray);
		}
	}
}