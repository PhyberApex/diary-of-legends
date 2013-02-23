package de.phyberapex.diaryoflegends;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.db4o.ext.DatabaseFileLockedException;

import de.phyberapex.diaryoflegends.base.Config;

public class ExitAction extends AbstractAction {

	private static final long serialVersionUID = 7802542358898096336L;
	private static ExitAction instance;
	private static Logger logger = LogManager.getLogger(ExitAction.class
			.getName());

	private ExitAction() {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			Config.getInstance().closeDatabase();
		} catch (DatabaseFileLockedException e) {
			logger.fatal("Databasefile is locked");
		}
		System.exit(0);
	}

	/**
	 * Returns an instance of this class if the static attribute instance is<br>
	 * null it will be created.
	 * 
	 * @return {@link ExiAction} an instance of this class
	 */
	public static synchronized ExitAction getInstance() {
		logger.trace("getInstance() - Entering");
		if (instance == null) {
			instance = new ExitAction();
		}
		logger.trace("getInstance() - Returning");
		logger.debug("getInstance() - Returning: {}", instance);
		return instance;
	}
}
