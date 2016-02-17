// ------------------------------------------------------------------------------
// Copyright (c) 2015 Microsoft Corporation
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.
// ------------------------------------------------------------------------------

// **NOTE** This file was generated by a tool and any changes will be overwritten.
package com.onedrive.sdk.generated;

import com.onedrive.sdk.concurrency.*;
import com.onedrive.sdk.core.*;
import com.onedrive.sdk.extensions.*;
import com.onedrive.sdk.http.*;
import com.onedrive.sdk.generated.*;
import com.onedrive.sdk.options.*;
import com.onedrive.sdk.serializer.*;

import java.util.*;

// **NOTE** This file was generated by a tool and any changes will be overwritten.

/**
 * The class for the Base Item Collection Request.
 */
public class BaseItemCollectionRequest extends BaseCollectionRequest<BaseItemCollectionResponse, IItemCollectionPage> implements IBaseItemCollectionRequest {

    /**
     * The request builder for this collection of Item
     *
     * @param requestUrl The request url
     * @param client The service client
     * @param options The options for this request
     */
    public BaseItemCollectionRequest(final String requestUrl, IOneDriveClient client, final List<Option> options) {
        super(requestUrl, client, options, BaseItemCollectionResponse.class, IItemCollectionPage.class);
    }

    public void get(final ICallback<IItemCollectionPage> callback) {
        final IExecutors executors = getBaseRequest().getClient().getExecutors();
        executors.performOnBackground(new Runnable() {
           @Override
           public void run() {
                try {
                    executors.performOnForeground(get(), callback);
                } catch (final ClientException e) {
                    executors.performOnForeground(e, callback);
                }
           }
        });
    }

    public IItemCollectionPage get() throws ClientException {
        final BaseItemCollectionResponse response = send();
        return buildFromResponse(response);
    }

    @Deprecated
    public void create(final Item newItem, final ICallback<Item> callback) {
        this.post(newItem, callback);
    }

    @Deprecated
    public Item create(final Item newItem) throws ClientException {
        return this.post(newItem);
    }

    public void post(final Item newItem, final ICallback<Item> callback) {
        final String requestUrl = getBaseRequest().getRequestUrl().toString();
        new ItemRequestBuilder(requestUrl, getBaseRequest().getClient(), /* Options */ null)
            .buildRequest()
            .post(newItem, callback);
    }

    public Item post(final Item newItem) throws ClientException {
        final String requestUrl = getBaseRequest().getRequestUrl().toString();
        return new ItemRequestBuilder(requestUrl, getBaseRequest().getClient(), /* Options */ null)
            .buildRequest()
            .post(newItem);
    }
    public IItemCollectionRequest expand(final String value) {
        addQueryOption(new QueryOption("expand", value));
        return (ItemCollectionRequest)this;
    }

    public IItemCollectionRequest select(final String value) {
        addQueryOption(new QueryOption("select", value));
        return (ItemCollectionRequest)this;
    }

    public IItemCollectionRequest top(final int value) {
        addQueryOption(new QueryOption("top", value + ""));
        return (ItemCollectionRequest)this;
    }

    public IItemCollectionPage buildFromResponse(final BaseItemCollectionResponse response) {
        final IItemCollectionRequestBuilder builder;
        if (response.nextLink != null) {
            builder = new ItemCollectionRequestBuilder(response.nextLink, getBaseRequest().getClient(), /* options */ null);
        } else {
            builder = null;
        }
        final ItemCollectionPage page = new ItemCollectionPage(response, builder);
        page.setRawObject(response.getSerializer(), response.getRawObject());
        return page;
    }
}
