package de.phyberapex.diaryoflegends;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.base.Config;

public class ExitAction extends AbstractAction{

	private static final long serialVersionUID = 7802542358898096336L;
	private static ExitAction instance;
	private static Logger logger = LogManager.getLogger(ExitAction.class.getName());
	
	
	private ExitAction(){
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Config.getInstance().getDBHandle().close();
		
		System.exit(0);
	}
	
	/**
	 * Returns an instance of this class if the static attribute instance is<br>
	 * null it will be created.
	 * @return {@link ExiAction} an instance of this class
	 */
	public static synchronized ExitAction getInstance() {
		logger.trace("getInstance() - Entering");
		logger.debug("Instance is {}", instance);
		if (instance == null) {
			logger.debug("Creating a new instance of ExitAction");
			instance = new ExitAction();
		}
		logger.trace("getInstance() - Returning");
		logger.debug("Returned {}", instance);
		return instance;
	}

}
