import { NgClass, NgIf } from '@angular/common';
import { Component, inject, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-video',
  standalone: true,
  imports: [NgClass, NgIf],
  templateUrl: './video.component.html',
  styleUrl: './video.component.css'
})
export class VideoComponent implements OnInit {
  @Input({required: true}) url = '';

  safeUrl: SafeResourceUrl | null = null;
  private sanitizer = inject(DomSanitizer);
  isYouTubeUrl(url: string): boolean {
    return url.includes('youtube.com') || url.includes('youtu.be');
  }

  ngOnInit(): void {
      const url = this.url;
      if (this.isYouTubeUrl(url)) {
        this.safeUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.getYouTubeEmbedUrl(url));
      } else if (this.isGoogleDriveUrl(url)) {
        this.safeUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.getGoogleDriveEmbedUrl(url));
      }
    }

  getYouTubeEmbedUrl(url: string): string {
    let videoId = '';
    if (url.includes('youtube.com')) {
      const urlParams = new URLSearchParams(new URL(url).search);
      videoId = urlParams.get('v') || '';
    } else if (url.includes('youtu.be')) {
      videoId = url.split('/').pop() || '';
    }
    return `https://www.youtube.com/embed/${videoId}?autoplay=1&loop=1&playlist=${videoId}`;
  }

  isGoogleDriveUrl(url: string): boolean {
    return url.includes('drive.google.com');
  }

  getGoogleDriveEmbedUrl(url: string): string {
    const fileIdMatch = url.match(/[-\w]{25,}/);
    const fileId = fileIdMatch ? fileIdMatch[0] : '';
    return `https://drive.google.com/file/d/${fileId}/preview?autoplay=1&loop=1`;
  }
}
