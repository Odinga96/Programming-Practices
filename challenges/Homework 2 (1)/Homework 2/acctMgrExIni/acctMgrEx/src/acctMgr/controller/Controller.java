package acctMgr.controller;
import acctMgr.view.View;
import acctMgr.model.Model;

/**
 *
 */
public interface Controller {
	/**
	 * @param model
	 */
	void setModel(Model model);
	Model getModel();
	View getView();

	/**
	 * @param view
	 */
	void setView(View view);
}
