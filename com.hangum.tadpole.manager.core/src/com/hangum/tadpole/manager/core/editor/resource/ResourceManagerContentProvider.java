/*******************************************************************************
 * Copyright (c) 2013 hangum.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     hangum - initial API and implementation
 ******************************************************************************/
package com.hangum.tadpole.manager.core.editor.resource;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

import com.hangum.tadpole.sql.dao.ManagerListDTO;
import com.hangum.tadpole.sql.dao.system.UserDBDAO;
import com.hangum.tadpole.sql.dao.system.UserDBResourceDAO;

/**
 * manager view의 content provider
 * @author hangum
 *
 */
public class ResourceManagerContentProvider extends ArrayContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getChildren(Object parentElement) {
		
		if(parentElement instanceof ManagerListDTO) {
			ManagerListDTO dto = (ManagerListDTO)parentElement;
			return dto.getManagerList().toArray();
		} else if(parentElement instanceof UserDBDAO) {
			UserDBDAO dto = (UserDBDAO)parentElement;
			return dto.getListUserDBErd().toArray();
		}
		
		return null;
	}

	@Override
	public Object getParent(Object element) {
		
		if(element instanceof UserDBDAO) {
			UserDBDAO dto = (UserDBDAO)element;
			return dto.getParent();
		} else if(element instanceof UserDBResourceDAO) {
			UserDBResourceDAO dao = (UserDBResourceDAO)element;
			return dao.getParent();
		}
		
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof ManagerListDTO) {
			ManagerListDTO dto = (ManagerListDTO)element;
			return dto.getManagerList().size() > 0;
		} else if(element instanceof UserDBDAO) {
			UserDBDAO dto = (UserDBDAO)element;
			if(dto.getListUserDBErd() == null) return false;
			else return dto.getListUserDBErd().size() > 0;
		}

		return false;
	}
	
}
