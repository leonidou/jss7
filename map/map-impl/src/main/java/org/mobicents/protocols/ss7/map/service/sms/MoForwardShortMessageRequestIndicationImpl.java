/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.protocols.ss7.map.service.sms;

import java.util.ArrayList;

import org.mobicents.protocols.asn.Tag;
import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentException;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.mobicents.protocols.ss7.map.api.primitives.IMSI;
import org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.mobicents.protocols.ss7.map.api.service.sms.MoForwardShortMessageRequestIndication;
import org.mobicents.protocols.ss7.map.api.service.sms.SM_RP_DA;
import org.mobicents.protocols.ss7.map.api.service.sms.SM_RP_OA;
import org.mobicents.protocols.ss7.map.primitives.IMSIImpl;
import org.mobicents.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.mobicents.protocols.ss7.tcap.api.ComponentPrimitiveFactory;
import org.mobicents.protocols.ss7.tcap.asn.comp.Parameter;


/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class MoForwardShortMessageRequestIndicationImpl extends SmsServiceImpl implements MoForwardShortMessageRequestIndication {
	
	private SM_RP_DA sm_RP_DA;
	private SM_RP_OA sm_RP_OA;
	private byte[] sm_RP_UI;
	private MAPExtensionContainer extensionContainer;
	private IMSI imsi;

	
	public MoForwardShortMessageRequestIndicationImpl() {
	}
	
	public MoForwardShortMessageRequestIndicationImpl(SM_RP_DA sm_RP_DA, SM_RP_OA sm_RP_OA, byte[] sm_RP_UI, MAPExtensionContainer extensionContainer, IMSI imsi) {
		this.sm_RP_DA = sm_RP_DA;
		this.sm_RP_OA = sm_RP_OA;
		this.sm_RP_UI = sm_RP_UI;
		this.extensionContainer = extensionContainer;
		this.imsi = imsi;
	}
	
	@Override
	public SM_RP_DA getSM_RP_DA() {
		return this.sm_RP_DA;
	}

	@Override
	public SM_RP_OA getSM_RP_OA() {
		return this.sm_RP_OA;
	}

	@Override
	public byte[] getSM_RP_UI() {
		return this.sm_RP_UI;
	}

	@Override
	public MAPExtensionContainer getExtensionContainer() {
		return this.extensionContainer;
	}

	@Override
	public IMSI getIMSI() {
		return this.imsi;
	}

	
	public void decode(Parameter parameter) throws MAPParsingComponentException {

		Parameter[] parameters = parameter.getParameters();

		if (parameters == null || parameters.length < 3)
			throw new MAPParsingComponentException("Error while decoding moForwardShortMessageRequest: Needs at least 3 mandatory parameters, found"
					+ parameters.length, MAPParsingComponentExceptionReason.MistypedParameter);

		// SM_RP_DA
		Parameter p = parameters[0];
		if (p.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !p.isPrimitive())
			throw new MAPParsingComponentException("Error while decoding moForwardShortMessageRequest: Parameter 0 bad tag class or not primitive",
					MAPParsingComponentExceptionReason.MistypedParameter);
		this.sm_RP_DA = new SM_RP_DAImpl();
		((SM_RP_DAImpl)this.sm_RP_DA).decode(p);

		// SM_RP_OA
		p = parameters[1];
		if (p.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !p.isPrimitive())
			throw new MAPParsingComponentException("Error while decoding moForwardShortMessageRequest: Parameter 1 bad tag class or not primitive",
					MAPParsingComponentExceptionReason.MistypedParameter);
		this.sm_RP_OA = new SM_RP_OAImpl();
		((SM_RP_OAImpl)this.sm_RP_OA).decode(p);

		// sm-RP-UI
		p = parameters[2];
		if (p.getTagClass() != Tag.CLASS_UNIVERSAL || !p.isPrimitive())
			throw new MAPParsingComponentException("Error while decoding moForwardShortMessageRequest: Parameter 2 bad tag class or not primitive",
					MAPParsingComponentExceptionReason.MistypedParameter);
		if (p.getTag() != Tag.STRING_OCTET)
			throw new MAPParsingComponentException("Error while decoding moForwardShortMessageRequest: Parameter 2 tag must be STRING_OCTET, found: "
					+ p.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
		this.sm_RP_UI = p.getData();

		this.extensionContainer = null;
		this.imsi = null;
		for (int i1 = 3; i1 < parameters.length; i1++) {
			p = parameters[i1];

			if (p.getTag() == Tag.SEQUENCE && p.getTagClass() == Tag.CLASS_UNIVERSAL) {
				if (p.isPrimitive())
					throw new MAPParsingComponentException("Error while decoding moForwardShortMessageRequest: Parameter extensionContainer is primitive",
							MAPParsingComponentExceptionReason.MistypedParameter);
				this.extensionContainer = new MAPExtensionContainerImpl();
				((MAPExtensionContainerImpl)this.extensionContainer).decode(p);
			} else if (p.getTag() == Tag.STRING_OCTET && p.getTagClass() == Tag.CLASS_UNIVERSAL) {
				if (!p.isPrimitive())
					throw new MAPParsingComponentException("Error while decoding moForwardShortMessageRequest: Parameter imsi is not primitive",
							MAPParsingComponentExceptionReason.MistypedParameter);
				this.imsi = new IMSIImpl();
				((IMSIImpl)this.imsi).decode(p);
			}
		}
	}

	public Parameter encode(ComponentPrimitiveFactory factory) throws MAPException {

		if (this.sm_RP_DA == null || this.sm_RP_OA == null || this.sm_RP_UI == null)
			throw new MAPException("sm_RP_DA,sm_RP_OA and sm_RP_UI must not be null");

		// Sequence of Parameter
		ArrayList<Parameter> lstPar = new ArrayList<Parameter>();

		Parameter p1 = ((SM_RP_DAImpl) sm_RP_DA).encode();
		lstPar.add(p1);

		Parameter p2 = ((SM_RP_OAImpl) sm_RP_OA).encode();
		lstPar.add(p2);

		Parameter p3 = factory.createParameter();
		p3.setTagClass(Tag.CLASS_UNIVERSAL);
		p3.setTag(Tag.STRING_OCTET);
		p3.setData(sm_RP_UI);
		lstPar.add(p3);

		Parameter p4 = null;
		if (extensionContainer != null) {
			p4 = ((MAPExtensionContainerImpl) extensionContainer).encode();
			lstPar.add(p4);
		}

		Parameter p5 = null;
		if (imsi != null) {
			p5 = ((IMSIImpl) imsi).encode();
			p5.setTagClass(Tag.CLASS_UNIVERSAL);
			p5.setTag(Tag.STRING_OCTET);
			lstPar.add(p5);
		}

		Parameter p = factory.createParameter();

		Parameter[] pp = new Parameter[lstPar.size()];
		lstPar.toArray(pp);
		p.setParameters(pp);

		return p;
	}
}

