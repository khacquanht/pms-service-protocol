package apigatewaywahsis.service;

import apigatewaywahsis.common.CommonModel;
import apigatewaywahsis.common.DefinedName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import com.wahsis.company_service.AppHandler;
import com.service_wahsis.company_service.AppHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khiemnv
 */
public class AppServlet extends AbstractServletNonAuthenticate {

    @Override
    protected void processs(HttpServletRequest req, HttpServletResponse resp, StringBuilder respContent) {
        try {
            StringBuilder vrfContent = new StringBuilder();
            String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
            String data = req.getParameter(DefinedName.PARAM_DATA);
            if (cmd != null && data != null) {
                AppHandler.getInstance().process(cmd, data, respContent, vrfContent);
            } else {
                respContent.append(CommonModel.toJSON(-1, DefinedName.RESP_MSG_INVALID_REQUEST));
            }
        } catch (Exception ex) {
            Logger.getLogger(AppServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected int authenticate(HttpServletRequest req, StringBuilder authenErrorMsg) {
        return 0;
    }
}
