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

package org.mobicents.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.mobicents.protocols.ss7.cap.api.CAPServiceListener;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPServiceCircuitSwitchedCallListener extends CAPServiceListener {

    void onInitialDPRequest(InitialDPRequest ind);

    void onRequestReportBCSMEventRequest(RequestReportBCSMEventRequest ind);

    void onApplyChargingRequest(ApplyChargingRequest ind);

    void onEventReportBCSMRequest(EventReportBCSMRequest ind);

    void onContinueRequest(ContinueRequest ind);

    void onApplyChargingReportRequest(ApplyChargingReportRequest ind);

    void onReleaseCallRequest(ReleaseCallRequest ind);

    void onConnectRequest(ConnectRequest ind);

    void onCallInformationRequestRequest(CallInformationRequestRequest ind);

    void onCallInformationReportRequest(CallInformationReportRequest ind);

    void onActivityTestRequest(ActivityTestRequest ind);

    void onActivityTestResponse(ActivityTestResponse ind);

    void onAssistRequestInstructionsRequest(AssistRequestInstructionsRequest ind);

    void onEstablishTemporaryConnectionRequest(EstablishTemporaryConnectionRequest ind);

    void onDisconnectForwardConnectionRequest(DisconnectForwardConnectionRequest ind);

    void onConnectToResourceRequest(ConnectToResourceRequest ind);

    void onResetTimerRequest(ResetTimerRequest ind);

    void onFurnishChargingInformationRequest(FurnishChargingInformationRequest ind);

    void onSendChargingInformationRequest(SendChargingInformationRequest ind);

    void onSpecializedResourceReportRequest(SpecializedResourceReportRequest ind);

    void onPlayAnnouncementRequest(PlayAnnouncementRequest ind);

    void onPromptAndCollectUserInformationRequest(PromptAndCollectUserInformationRequest ind);

    void onPromptAndCollectUserInformationResponse(PromptAndCollectUserInformationResponse ind);

    void onCancelRequest(CancelRequest ind);

}