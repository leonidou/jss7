/*
 * TeleStax, Open Source Cloud Communications  Copyright 2012.
 * and individual contributors
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

package org.mobicents.protocols.ss7.map.api.service.mobility.handover;

import java.util.ArrayList;
import org.mobicents.protocols.ss7.map.api.primitives.AccessNetworkSignalInfo;
import org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.mobicents.protocols.ss7.map.api.service.mobility.MobilityMessage;
import org.mobicents.protocols.ss7.map.api.service.oam.TracePropagationList;

/**
 * 

MAP V3:
ForwardAccessSignalling-Arg ::= [3] SEQUENCE {
	an-APDU		AccessNetworkSignalInfo,
	integrityProtectionInfo	[0] IntegrityProtectionInformation		OPTIONAL,
	encryptionInfo	[1] EncryptionInformation		OPTIONAL,
	keyStatus		[2]	KeyStatus	OPTIONAL,
	allowedGSM-Algorithms	[4]	AllowedGSM-Algorithms	OPTIONAL,
	allowedUMTS-Algorithms	[5]	AllowedUMTS-Algorithms	OPTIONAL,
	radioResourceInformation	[6] RadioResourceInformation	OPTIONAL,
	extensionContainer	[3]	ExtensionContainer 	OPTIONAL,
	...,
	radioResourceList	[7]	RadioResourceList	OPTIONAL,
	bssmap-ServiceHandover	[9]	BSSMAP-ServiceHandover	OPTIONAL,
	ranap-ServiceHandover	[8]	RANAP-ServiceHandover	OPTIONAL,
	bssmap-ServiceHandoverList	[10]	BSSMAP-ServiceHandoverList	OPTIONAL,
	currentlyUsedCodec	[11] Codec	OPTIONAL,
	iuSupportedCodecsList	[12] SupportedCodecsList	OPTIONAL,
	rab-ConfigurationIndicator	[13] NULL		OPTIONAL,
	iuSelectedCodec	[14]	Codec	OPTIONAL,
	alternativeChannelType	[15]	RadioResourceInformation	OPTIONAL,
	tracePropagationList	[17]	TracePropagationList	OPTIONAL,
 	aoipSupportedCodecsListAnchor	[18] AoIPCodecsList	OPTIONAL,
 	aoipSelectedCodecTarget	[19] AoIPCodec	OPTIONAL }

MAP V2:
ForwardAccessSignalling ::= OPERATION--Timer s
ARGUMENT
bss-APDU	ExternalSignalInfo

RadioResourceList ::= SEQUENCE SIZE (1..7) OF RadioResource

BSSMAP-ServiceHandoverList ::= SEQUENCE SIZE (1..7) OF BSSMAP-ServiceHandoverInfo

 * 
 * @author sergey vetyutnev
 * 
 */
public interface ForwardAccessSignallingRequest extends MobilityMessage {

	public AccessNetworkSignalInfo getAnAPDU();

	public IntegrityProtectionInformation getIntegrityProtectionInfo();

	public EncryptionInformation getEncryptionInfo();

	public KeyStatus getKeyStatus();

	public AllowedGSMAlgorithms getAllowedGSMAlgorithms();

	public AllowedUMTSAlgorithms getAllowedUMTSAlgorithms();

	public RadioResourceInformation getRadioResourceInformation();

	public MAPExtensionContainer getExtensionContainer();

	public ArrayList<RadioResource> getRadioResourceList();

	public BSSMAPServiceHandover getBSSMAPServiceHandover();

	public RANAPServiceHandover getRANAPServiceHandover();

	public ArrayList<BSSMAPServiceHandoverInfo> getBSSMAPServiceHandoverList();

	public Codec getCurrentlyUsedCodec();

	public SupportedCodecsList getIuSupportedCodecsList();

	public boolean getRabConfigurationIndicator();

	public Codec getIuSelectedCodec();

	public RadioResourceInformation getAlternativeChannelType();

	public TracePropagationList getTracePropagationList();

	public AoIPCodecsList getAoipSupportedCodecsListAnchor();

	public AoIPCodec getAoipSelectedCodecTarget();

}