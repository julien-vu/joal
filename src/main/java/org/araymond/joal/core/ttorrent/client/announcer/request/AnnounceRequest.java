package org.araymond.joal.core.ttorrent.client.announcer.request;

import com.google.common.base.Objects;
import com.turn.ttorrent.common.protocol.TrackerMessage.AnnounceRequestMessage.RequestEvent;
import org.araymond.joal.core.torrent.torrent.InfoHash;
import org.araymond.joal.core.ttorrent.client.DelayQueue;
import org.araymond.joal.core.ttorrent.client.announcer.Announcer;

public final class AnnounceRequest implements DelayQueue.InfoHashAble {

    private final Announcer announcer;
    private final RequestEvent event;

    private AnnounceRequest(final Announcer announcer, final RequestEvent event) {
        this.announcer = announcer;
        this.event = event;
    }

    public static AnnounceRequest createStart(final Announcer announcer) {
        return new AnnounceRequest(announcer, RequestEvent.STARTED);
    }

    public static AnnounceRequest createRegular(final Announcer announcer) {
        return new AnnounceRequest(announcer, RequestEvent.NONE);
    }

    public static AnnounceRequest createStop(final Announcer announcer) {
        return new AnnounceRequest(announcer, RequestEvent.STOPPED);
    }

    public Announcer getAnnouncer() {
        return this.announcer;
    }

    public RequestEvent getEvent() {
        return this.event;
    }

    @Override
    public InfoHash getInfoHash() {
        return this.announcer.getTorrentInfoHash();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AnnounceRequest that = (AnnounceRequest) o;
        return Objects.equal(announcer, that.announcer);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(announcer);
    }
}
