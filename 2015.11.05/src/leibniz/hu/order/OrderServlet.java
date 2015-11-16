package leibniz.hu.order;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leibniz.hu.addr.AddrService;
import leibniz.hu.addr.Address;
import leibniz.hu.user.User;
import leibniz.hu.utils.BaseServlet;

public class OrderServlet extends BaseServlet {

	private static final long serialVersionUID = 7086692819356121842L;
	private AddrService addrServ = new AddrService();
	/* (non-Javadoc)
	 * @see leibniz.hu.utils.BaseServlet#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * Ĭ�ϵķ��������û��ύ������ʱ��
	 * �Ȳ�ѯ���ݿ��address����ȡ��ǰ�û������б���ĵ�ַ
	 * �Ա��ڶ���ȷ��ҳ�湩�û�ѡ������ĵ�ַ
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		//��Session��ȡ��ǰ��User���󣬲�ͨ��AddressService��ѯ���û������д洢���ͻ���ַ
		User user = (User) req.getSession().getAttribute("user");
		List<Address> addrList = addrServ.queryByUserid(user.getId());
		//����request�У���ת������Ӧ�Ķ���ȷ��ҳ��
		req.setAttribute("addrlist", addrList);
		return "order";
	}

	public String create(HttpServletRequest req, HttpServletResponse resp){
		return null;
	}
}
