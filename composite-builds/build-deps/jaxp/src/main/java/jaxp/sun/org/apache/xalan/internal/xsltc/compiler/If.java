/*
 *  This file is part of AndroidIDE.
 *
 *  AndroidIDE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  AndroidIDE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *   along with AndroidIDE.  If not, see <https://www.gnu.org/licenses/>.
 */

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * Copyright 2001-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * $Id: If.java,v 1.2.4.1 2005/09/01 15:39:47 pvedula Exp $
 */

package jaxp.sun.org.apache.xalan.internal.xsltc.compiler;

import jaxp.sun.org.apache.bcel.internal.generic.InstructionHandle;
import jaxp.sun.org.apache.bcel.internal.generic.InstructionList;
import jaxp.sun.org.apache.xalan.internal.xsltc.compiler.util.BooleanType;
import jaxp.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import jaxp.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import jaxp.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import jaxp.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import jaxp.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import jaxp.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 * @author Morten Jorgensen
 */
final class If extends Instruction {

    private Expression _test;
    private boolean    _ignore = false;

    /**
     * Display the contents of this element
     */
    public void display(int indent) {
        indent(indent);
        Util.println("If");
        indent(indent + IndentIncrement);
        System.out.print("test ");
        Util.println(_test.toString());
        displayContents(indent + IndentIncrement);
    }

    /**
     * Parse the "test" expression and contents of this element.
     */
    public void parseContents(Parser parser) {
        // Parse the "test" expression
        _test = parser.parseExpression(this, "test", null);

        // Make sure required attribute(s) have been set
        if (_test.isDummy()) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, "test");
            return;
        }

        // Ignore xsl:if when test is false (function-available() and
        // element-available())
        Object result = _test.evaluateAtCompileTime();
        if (result != null && result instanceof Boolean) {
            boolean value = (result != null) ? (boolean) result : false;;
            _ignore = !value;
        }

        parseChildren(parser);
    }

    /**
     * Type-check the "test" expression and contents of this element.
     * The contents will be ignored if we know the test will always fail.
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        // Type-check the "test" expression
        if (_test.typeCheck(stable) instanceof BooleanType == false) {
            _test = new CastExpr(_test, Type.Boolean);
        }
        // Type check the element contents
        if (!_ignore) {
            typeCheckContents(stable);
        }
        return Type.Void;
    }

    /**
     * Translate the "test" expression and contents of this element.
     * The contents will be ignored if we know the test will always fail.
     */
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        _test.translateDesynthesized(classGen, methodGen);
        // remember end of condition
        final InstructionHandle truec = il.getEnd();
        if (!_ignore) {
            translateContents(classGen, methodGen);
        }
        _test.backPatchFalseList(il.append(NOP));
        _test.backPatchTrueList(truec.getNext());
    }
}
