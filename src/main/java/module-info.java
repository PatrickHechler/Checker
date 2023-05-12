// This file is part of the Checker Project
// DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
// Copyright (C) 2023 Patrick Hechler
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program. If not, see <https://www.gnu.org/licenses/>.

/**
 * this module is intended to help checking java projects
 * 
 * @author Patrick Hechler
 */
module de.hechler.patrick.zeugs.check {
	
	requires jdk.unsupported;
	requires transitive java.logging;
	
	exports de.hechler.patrick.zeugs.check.anotations;
	exports de.hechler.patrick.zeugs.check.exceptions;
	exports de.hechler.patrick.zeugs.check.interfaces;
	exports de.hechler.patrick.zeugs.check.objects;
	
}
