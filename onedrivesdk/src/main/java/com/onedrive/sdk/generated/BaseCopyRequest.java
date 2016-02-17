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
 * The class for the Base Copy Request.
 */
public class BaseCopyRequest extends BaseRequest implements IBaseCopyRequest {
    protected final CopyBody mBody;

    /**
     * The request for this Copy
     *
     * @param requestUrl The request url
     * @param client The service client
     * @param options The options for this request
     */
    public BaseCopyRequest(final String requestUrl, final IOneDriveClient client, final List<Option> options, final String name, final ItemReference parentReference) {
        super(requestUrl, client, options, AsyncMonitorLocation.class);
        mBody = new CopyBody();
        mBody.name = name;
        mBody.parentReference = parentReference;
        addHeader("Prefer", "respond-async");
    }

    @Deprecated
    public void create(final ICallback<AsyncMonitor<Item>> callback) {
        this.post(callback);
    }

    @Deprecated
    public AsyncMonitor<Item> create() throws ClientException {
        return this.post();
    }

    public void post(final ICallback<AsyncMonitor<Item>> callback) {
        getClient().getExecutors().performOnBackground(new Runnable() {
            @Override
            public void run() {
                try {
                    getClient().getExecutors().performOnForeground(post(), callback);
                } catch (final ClientException e) {
                    getClient().getExecutors().performOnForeground(e, callback);
                }
            }
        });
    }

    public AsyncMonitor<Item> post() throws ClientException {
        final AsyncMonitorLocation monitorLocation = send(HttpMethod.POST, mBody);

        return new AsyncMonitor<>(getClient(), monitorLocation, new ResultGetter<Item>() {
            @Override
            public Item getResultFrom(final String resourceUrl, final IOneDriveClient client) {
                return new ItemRequest(resourceUrl, client, /* options */ null).get();
            }
        });
    }

    public ICopyRequest select(final String value) {
        getQueryOptions().add(new QueryOption("select", value));
        return (CopyRequest)this;
    }

    public ICopyRequest top(final int value) {
        getQueryOptions().add(new QueryOption("top", value+""));
        return (CopyRequest)this;
    }

    public ICopyRequest expand(final String value) {
        getQueryOptions().add(new QueryOption("expand", value));
        return (CopyRequest)this;
    }
}
