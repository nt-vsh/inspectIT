package rocks.inspectit.ui.rcp.tester;

import org.eclipse.core.expressions.PropertyTester;

import rocks.inspectit.shared.cs.storage.recording.RecordingState;
import rocks.inspectit.ui.rcp.provider.ICmrRepositoryAndAgentProvider;
import rocks.inspectit.ui.rcp.provider.ICmrRepositoryProvider;
import rocks.inspectit.ui.rcp.provider.IInputDefinitionProvider;
import rocks.inspectit.ui.rcp.provider.IStorageDataProvider;
import rocks.inspectit.ui.rcp.repository.CmrRepositoryDefinition;
import rocks.inspectit.ui.rcp.repository.RepositoryDefinition;
import rocks.inspectit.ui.rcp.repository.CmrRepositoryDefinition.OnlineStatus;

/**
 * Tester for CMR Online Status.
 * 
 * @author Ivan Senic
 * 
 */
public class CmrOnlineStatusTester extends PropertyTester {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		CmrRepositoryDefinition cmrRepositoryDefinition = null;
		if (receiver instanceof ICmrRepositoryProvider) {
			cmrRepositoryDefinition = ((ICmrRepositoryProvider) receiver).getCmrRepositoryDefinition();
		} else if (receiver instanceof ICmrRepositoryAndAgentProvider) {
			cmrRepositoryDefinition = ((ICmrRepositoryAndAgentProvider) receiver).getCmrRepositoryDefinition();
		} else if (receiver instanceof IStorageDataProvider) {
			cmrRepositoryDefinition = ((IStorageDataProvider) receiver).getCmrRepositoryDefinition();
		} else if (receiver instanceof IInputDefinitionProvider) {
			RepositoryDefinition repository = ((IInputDefinitionProvider) receiver).getInputDefinition().getRepositoryDefinition();
			if (repository instanceof CmrRepositoryDefinition) {
				cmrRepositoryDefinition = (CmrRepositoryDefinition) repository;
			} else {
				return false;
			}
		} else {
			return false;
		}

		if ("onlineStatus".equals(property)) {
			if ("ONLINE".equals(expectedValue)) {
				return cmrRepositoryDefinition.getOnlineStatus() == OnlineStatus.ONLINE;
			} else if ("OFFLINE".equals(expectedValue)) {
				return cmrRepositoryDefinition.getOnlineStatus() == OnlineStatus.OFFLINE;
			} else if ("CHECKING".equals(expectedValue)) {
				return cmrRepositoryDefinition.getOnlineStatus() == OnlineStatus.CHECKING;
			}
		} else if ("recordingActive".equals(property)) {
			if (expectedValue instanceof Boolean) {
				if (cmrRepositoryDefinition.getOnlineStatus() != OnlineStatus.OFFLINE) {
					boolean recordingActive = cmrRepositoryDefinition.getStorageService().getRecordingState() != RecordingState.OFF;
					return ((Boolean) expectedValue).booleanValue() == recordingActive;
				} else {
					return false;
				}
			}
		}
		return false;
	}

}
