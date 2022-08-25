package com.ewallet.ewallet_admin.service.definition;

import com.ewallet.ewallet_admin.entity.NidCard;

import java.util.UUID;

public interface NidCardService
{
    NidCard getNidCard(UUID id);
}
